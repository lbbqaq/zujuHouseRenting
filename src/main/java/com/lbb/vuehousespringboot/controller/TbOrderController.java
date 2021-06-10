package com.lbb.vuehousespringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.dto.LoginDto;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbOrder;
import com.lbb.vuehousespringboot.entity.TbUser;
import com.lbb.vuehousespringboot.service.TbHouseBaseService;
import com.lbb.vuehousespringboot.service.TbOrderService;
import com.lbb.vuehousespringboot.service.TbUserService;
import com.lbb.vuehousespringboot.util.EmailTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-03-03
 */
@RestController
@RequestMapping("/tb-order")
public class TbOrderController {


    @Autowired
    private TbOrderService tbOrderService;
    @Autowired
    private TbHouseBaseService tbHouseBaseService;
    @Autowired
    private TbUserService tbUserService;
    @Autowired
    private EmailTool emailTool;

    @PostMapping("/submitOrder")
    public Result submitOrder(@Validated @RequestBody TbOrder order, HttpServletRequest request, HttpServletResponse response) {
//        存进Order表里
        tbOrderService.saveOrUpdate(order);
        TbHouseBase houseBase = tbHouseBaseService.getById(order.getHouseId());
        Short state = 2;
        houseBase.setHouseState(state);
//        更改房源状态
        tbHouseBaseService.updateById(houseBase);
        return Result.succful(order);
    }


    @GetMapping("/findOrderList")
    public Result GetOrderList(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = tbOrderService.findAllOrder(page, new QueryWrapper<TbOrder>()
                .eq("1", 1)
                .eq("deleted",0)
                .orderByDesc("create_time"));
        return Result.succful(pageData);
    }

    @GetMapping("/findOrderByKeyWord")
    public Result findOrderByKeyWord(@RequestParam("keyword") String keyword) {
        System.out.println(keyword);
        Page page = new Page(1, 5);
        IPage pageData = tbOrderService.findAllOrder(page, new QueryWrapper<TbOrder>()
                .eq("1", 1)
                .eq("deleted",0)
                .eq("id",keyword)
                .orderByDesc("create_time"));
        return Result.succful(pageData);
    }

    @GetMapping("/findOrderListByHostId")
    public Result findOrderListByHostId(@RequestParam Long hostId, @RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = tbOrderService.findAllOrder(page, new QueryWrapper<TbOrder>()
                .eq("host_id", hostId)
                .eq("deleted",0));
        return Result.succful(pageData);
    }

    @GetMapping("/findOrderListByUserId")
    public Result findOrderListByUserId(@RequestParam Long userId, @RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = tbOrderService.findAllOrder(page, new QueryWrapper<TbOrder>()
                .eq("user_id", userId)
                .eq("deleted",0)
        .orderByDesc("create_time"))
                ;
        return Result.succful(pageData);
    }

    @GetMapping("/findOrderById")
    public Result findOrderById(@RequestParam Long id) {
        TbOrder order = tbOrderService.findOrder(new QueryWrapper<TbOrder>()
                .eq("id", id)
                .eq("deleted",0));
        return Result.succful(order);
    }

    @GetMapping("/findOrderByHouseId")
    public Result findOrderByHouseId(@RequestParam Long houseId) {
        TbOrder order = tbOrderService.findOrder(new QueryWrapper<TbOrder>()
                .eq("house_id", houseId)
                .eq("deleted",0));
        return Result.succful(order);
    }

    @PostMapping("/updateOrderStateById")
    public Result updateOrderStateById(@RequestParam Long id, @RequestParam Integer state) {
        System.out.println("/updateOrderStateById");
        System.out.println(id);
        System.out.println(state);
        TbOrder order = tbOrderService.findOrder(new QueryWrapper<TbOrder>()
                .eq("id", id)
                .eq("deleted",0));
        order.setState(state);
        tbOrderService.saveOrUpdate(order);
        return Result.succful(order);
    }

    @GetMapping("/updateByOverState")
    public Result updateByOverState(@RequestParam Long id, @RequestParam Integer overState) {
        System.out.println("/updateByOverState");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("1532771197@qq.com");
//        System.out.println(id);
//        System.out.println(overState);
        TbOrder order = tbOrderService.findOrder(new QueryWrapper<TbOrder>()
                .eq("id", id).eq("deleted",0));
        TbUser tbUser = tbUserService.getById(order.getUserId());
        TbHouseBase tbHouseBase=tbHouseBaseService.getById(order.getHouseId());
        String email = tbUser.getEmail();
        System.out.println(email);
        simpleMailMessage.setTo((email));
        simpleMailMessage.setSubject("竹居通知，您的租房有新消息");
        switch (overState) {
//            申请退租
            case 1:
                order.setState(3);
                order.setOverState(overState);
                order.setOverTime(LocalDateTime.now());
//                order.setDeleted(1);
                break;
//            退租成功
            case 2:
                order.setState(4);
                Short state=1;
                tbHouseBase.setHouseState(state);
                tbHouseBaseService.updateById(tbHouseBase);
                order.setOverState(overState);
                simpleMailMessage.setText("尊敬的用户您的申请退租请求已同意，详细通知请上竹居网站查看更新");
                System.out.println("case2");
                break;
//            退租失败
            case 3:
                order.setOverState(overState);
                order.setState(2);
                simpleMailMessage.setText("尊敬的用户您的申请退租请求已被拒绝，详细通知请上竹居网站查看更新");
                System.out.println("case3");
                break;
            default:
                break;
        }

        tbOrderService.saveOrUpdate(order);
        emailTool.send(simpleMailMessage);
        return Result.succful(order);
    }


    @GetMapping("/cancleContract")
    public Result cancleContract(@RequestParam Long id,@RequestParam Long houseId){
        TbHouseBase houseBase = tbHouseBaseService.getById(houseId);
        houseBase.setHouseState((short) 1);
        tbHouseBaseService.saveOrUpdate(houseBase);
        boolean b = tbOrderService.removeById(id);
        return Result.succful(b);
    }

}
