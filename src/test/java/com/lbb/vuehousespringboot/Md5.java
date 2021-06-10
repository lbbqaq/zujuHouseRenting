package com.lbb.vuehousespringboot;

import cn.hutool.crypto.SecureUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Md5 {


    @Test
    public void Md5(){
        System.out.println(SecureUtil.md5("111111"));

    }

    @Test
    public void sb(){
        System.out.println("不限" != "不限");
        System.out.println(!"不限".equals("不限"));

    }



}
