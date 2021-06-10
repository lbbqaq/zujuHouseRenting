package com.lbb.vuehousespringboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class EmailTool {

    //引入javaMailSender对象
    @Resource
    JavaMailSender javaMailSender;

    //创建发送方法
    public String send(SimpleMailMessage simpleMailMessage){
//        /*创建简单邮件对象*/
//        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
//        /*设置发件人地址 此处要跟springboot配置文件中的username属性相同*/
//        simpleMailMessage.setFrom("1532771197@qq.com");
//        /*收件人地址*/
//        simpleMailMessage.setTo(("1532771197@qq.com"));
//        //设置邮件标题
//        simpleMailMessage.setSubject("邮件标题");
//        //设置邮件内容
//        simpleMailMessage.setText("sb卢哥");
        //发送邮件
        javaMailSender.send(simpleMailMessage);
        return "邮件发送成功";
    }
}
