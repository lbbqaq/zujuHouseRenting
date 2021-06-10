package com.lbb.vuehousespringboot.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.dto.LoginDto;
import com.lbb.vuehousespringboot.common.dto.PasswordDto;
import com.lbb.vuehousespringboot.common.dto.UserFilterDto;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.common.vo.UserVo;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbUser;
import com.lbb.vuehousespringboot.service.TbUserService;
import com.lbb.vuehousespringboot.util.*;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lbb
 * @since 2020-12-13
 */
@RestController
public class TbUserController {

    @Autowired
    TbUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private EmailTool emailTool;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        //判断
        TbUser user = userService.getOne(new QueryWrapper<TbUser>().eq("username", loginDto.getUsername()));
        //断言处理
        //验证验证码
        String keyCode = (String) request.getServletContext().getAttribute("code");
        String code = loginDto.getYzm();
        if (!code.equalsIgnoreCase(keyCode)) {
//            验证码不正确
            return Result.fail("验证码不正确");
        }
        Assert.notNull(user, "用户不存在");
//        md5加密
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }

        if (user.getStatus() == 0) {
//            验证码不正确
            return Result.fail("账号不可用，请找管理员!");
        }
//        获得token
        String jwt = jwtUtils.generateToken(user.getId());
        //放在header里面，如果以后要延期，就可以直接找不需要再弄新的接口
        //传过去的类似下面这串东西Cookie
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3NjE2MTM5LCJleHAiOjE2MDgyMjA5Mzl9.-fzhG9YLo4B_K5dndT_nSMfnbX0x1xQUq02tdHHdnkAllao0zMeBMI_8QyWjyByfQTOZnhNsrzqDB08pL1cXCw
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        user.setLastLogin(LocalDateTime.now());
        userService.updateById(user);
        System.out.println(user);
        return Result.succful(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("realname", user.getRealName())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    //@RequiresAuthentication需求验证
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succful(null);
    }

    @GetMapping("/findUserById")
    public Result findUserById(@RequestParam Long userId) {

        TbUser user = userService.getById(userId);
        if (user == null) {
            return Result.fail("找不到用户");
        }
        return Result.succful(user);
    }

    @PostMapping("/findAllUser")
    public Result findAllUser(@RequestBody UserFilterDto userFilterDto) {
        System.out.println(userFilterDto);
        QueryWrapper<TbUser> qw = new QueryWrapper<>();
        qw.eq("1",1);
        Page page = new Page(userFilterDto.getCurrentPage(), 5);
        System.out.println(userFilterDto);
        if (userFilterDto.getFilterState() != -1) {
            qw.eq("status", userFilterDto.getFilterState());
        }
        if (!userFilterDto.getKeyword().equals("")) {
            qw.like("username", userFilterDto.getKeyword())
                    .or()
                    .like("real_name", userFilterDto.getKeyword())
                    .or()
                    .like("id", userFilterDto.getKeyword());
        }

        IPage pageData = userService.page(page, qw);
        return Result.succful(pageData);
    }

    @GetMapping("/findAllUser2")
    public Result findAllUser2() {
        List<TbUser> list = userService.list(new QueryWrapper<TbUser>());
//        List<UserVo> list1=new ArrayList<>();
        List<UserVo> userVos = BeanConvertUtils.convertListTo(list, UserVo::new);

        System.out.println(userVos);
//        System.out.println(list);
        return Result.succful(userVos);
    }

    @GetMapping("/findUserByUserName")
    public Result findUserByUserName(@RequestParam("username") String username, HttpServletRequest request, HttpServletResponse response) {
        List<TbUser> list = userService.list(new QueryWrapper<TbUser>());
        TbUser user = userService.getOne(new QueryWrapper<TbUser>().eq("username", username));
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1532771197@qq.com");
        if (user != null) {
            String email = user.getEmail();
            simpleMailMessage.setTo((email));
            simpleMailMessage.setSubject("竹居通知，您的账号有新变化");
            String emailCode = VerifyCodeUtils.generateVerifyCode(6);
            //2.将验证码放入servletContext作用域
            request.getServletContext().setAttribute("emailCode", emailCode);
            simpleMailMessage.setText("您修改密码的验证码：" + emailCode);

            emailTool.send(simpleMailMessage);
            return Result.succful("邮件已发送");
        } else {
            return Result.fail("用户不存在");
        }


//        System.out.println(list);
//        return Result.succful();
    }

    @GetMapping("/checkEmailCode")
    public Result checkEmailCode(@RequestParam("emailCode") String emailCode, @RequestParam("username") String username, HttpServletRequest
            request, HttpServletResponse response) {
//        System.out.println(emailCode);
//        System.out.println( (String)request.getServletContext().getAttribute("emailCode"));
        if (emailCode.equals((String) request.getServletContext().getAttribute("emailCode"))) {
            return Result.succful("成功");
        }
        return Result.fail("失败");
    }

    @GetMapping("/updatePassword")
    public Result updatePassword(@RequestParam("password") String password, @RequestParam("username") String username, HttpServletRequest
            request, HttpServletResponse response) {
        System.out.println(password);
        System.out.println(username);
        TbUser user = userService.getOne(new QueryWrapper<TbUser>().eq("username", username));
        user.setPassword(SecureUtil.md5(password));
        boolean b = userService.saveOrUpdate(user);
        return Result.succful("修改成功");
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody TbUser tbUser) {
        if (tbUser.getId() != null) {
            System.out.println(tbUser);
            userService.saveOrUpdate(tbUser);
        } else {
            System.out.println("注册");
            register(tbUser);
        }

        return Result.succful("修改成功");
    }

    @PostMapping("/updateUserPassword")
    public Result updateUserPassword(@RequestBody PasswordDto passwordDto) {
        System.out.println(passwordDto);
        TbUser user = userService.getById(passwordDto.getId());
        System.out.println(user);
        Assert.notNull(user, "用户不存在");
        if (!SecureUtil.md5(passwordDto.getOldPassword()).equals(user.getPassword())) {
            return Result.fail("旧密码错误");
        } else if (!passwordDto.getNewPassword().equals(passwordDto.getNewPassword())) {
            return Result.fail("确认密码错误");
        } else {
            user.setPassword(SecureUtil.md5(passwordDto.getNewPassword()));
            userService.updateById(user);
        }
        return Result.succful("修改成功");
    }


    @PostMapping("/register")
    public Result register(@RequestBody TbUser tbUser) {
        tbUser.getUsername();
        TbUser username = userService.getOne(new QueryWrapper<TbUser>().eq("username", tbUser.getUsername()));
        if (username == null) {
            tbUser.setPassword(SecureUtil.md5(tbUser.getPassword()));
            System.out.println(tbUser.getPassword());
            tbUser.setCreated(LocalDateTime.now());
            tbUser.setLastLogin(LocalDateTime.now());
            tbUser.setStatus(1);
            tbUser.setPower(1);
            boolean save = userService.save(tbUser);
            return Result.succful("注册成功");
        } else {
            return Result.fail("账号已存在，注册失败");
        }


    }

    @PostMapping("/uploadFile")
    public Result uploadFile(@RequestParam("files") MultipartFile multipartFile) throws FileNotFoundException {
        System.out.println(multipartFile);
        //获取文件的名称
        final String fileName = multipartFile.getOriginalFilename();
        //封装成图片路径
        String filePath = PictureUtil.UserAvar(multipartFile.getOriginalFilename());
        System.out.println(filePath);
//        新建文件
        File file = new File(filePath);
//        保存
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 文件存储
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.succful("上传成功");
    }


    @GetMapping("findUserByQW")
    public Result findUserByQW(@RequestParam String keyword) {
        TbUser user = userService.getOne(new QueryWrapper<TbUser>()
                        .like("real_name", keyword)
                        .or()
                        .like("username", keyword)
//                .or()
//                .like("id", keyword)
        );
        return Result.succful(user);
    }

    @GetMapping("deleteUser")
    public Result deleteUser(@RequestParam Long id) {
        if (id != null) {
            userService.removeById(id);
        } else {
            return Result.fail("用户不存在！");
        }
        return Result.succful("");
    }


}
