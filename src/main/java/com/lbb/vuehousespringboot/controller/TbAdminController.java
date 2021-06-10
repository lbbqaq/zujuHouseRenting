package com.lbb.vuehousespringboot.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lbb.vuehousespringboot.common.dto.LoginDto;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbAdmin;
import com.lbb.vuehousespringboot.service.TbAdminService;
import com.lbb.vuehousespringboot.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-02-28
 */
@RestController
@RequestMapping("/tb-admin")
public class TbAdminController {

    @Autowired
    private TbAdminService adminService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request, HttpServletResponse response) {
        //判断
        TbAdmin admin = adminService.getOne(new QueryWrapper<TbAdmin>().eq("username", loginDto.getUsername()));
        //断言处理
        //验证验证码
        String keyCode = (String) request.getServletContext().getAttribute("code");
        String code =loginDto.getYzm();
        if (!code.equalsIgnoreCase(keyCode)) {
//            验证码不正确
            return Result.fail("验证码不正确");
        }
        Assert.notNull(admin, "用户不存在");
//        md5加密
        if (!admin.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码不正确");
        }
//        获得token
        String jwt = jwtUtils.generateToken(admin.getId());
        //放在header里面，如果以后要延期，就可以直接找不需要再弄新的接口
        //传过去的类似下面这串东西Cookie
        //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjA3NjE2MTM5LCJleHAiOjE2MDgyMjA5Mzl9.-fzhG9YLo4B_K5dndT_nSMfnbX0x1xQUq02tdHHdnkAllao0zMeBMI_8QyWjyByfQTOZnhNsrzqDB08pL1cXCw
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succful(MapUtil.builder()
                .put("id", admin.getId())
                .put("username", admin.getUsername())
                .put("avatar", admin.getAvatar())
                .put("email", admin.getEmail())
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

}
