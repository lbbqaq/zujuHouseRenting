package com.lbb.vuehousespringboot.service;

import cn.hutool.extra.template.engine.velocity.VelocityEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.lbb.vuehousespringboot.util.EmailTool;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class MailSendServiceTest {

//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Autowired
//    private EmailTool tool;
//
//
//    @Autowired
//    private VelocityEngine velocityEngine;
//    @Test
//    public void sendSimpleMail() throws Exception {
////        tool.send();
//    }

}