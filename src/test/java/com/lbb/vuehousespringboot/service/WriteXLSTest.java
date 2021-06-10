package com.lbb.vuehousespringboot.service;

import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Reader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class WriteXLSTest {

//
//    @Autowired
//    private  WriteXLS writeXLS;
//    @Autowired
//    private  ReadXLS readXLS;
//    @Autowired
//    private  TbUserService tbUserService;
//    @Autowired
//    private  TbHouseBaseService tbHouseBaseService;
//
//
//    @Test
//    void exportExcelUser() {
//        List<TbUser> list = tbUserService.list();
//        System.out.println(list);
//        writeXLS.exportExcelUser(list);
//        System.out.println("完成");
//    }
//
//    @Test
//    void exportExcelHouse() {
//        List<TbHouseBase> list = tbHouseBaseService.list();
//        System.out.println(list);
//        writeXLS.exportExcelHouse(list);
//        System.out.println("完成");
//    }
//    @Test
//    void writeExcelHouse() {
////        List<TbHouseBase> tbHouseBases = readXLS.importXLS();
////        for (TbHouseBase tbHouseBase:tbHouseBases){
////            System.out.println(tbHouseBase);
////            System.out.println("----------");
////        }
////        System.out.println("完成");
//    }
}