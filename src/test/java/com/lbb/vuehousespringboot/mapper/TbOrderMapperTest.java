package com.lbb.vuehousespringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TbOrderMapperTest {

//    @Autowired
//    private TbOrderMapper tbOrderMapper;
//
//    @Test
//    void findAllOrderByHostId() {
//        Long hostId = 1L;
//        System.out.println(tbOrderMapper.findAllOrderByHostId(hostId, new QueryWrapper<TbOrder>().eq("host_id", hostId)));
//
//    }
//
//    @Test
//    void findAllOrder() {
//        Long hostId = 1L;
//        IPage pageData = tbOrderMapper.findAllOrder(new Page(), new QueryWrapper<TbOrder>());
//        System.out.println(pageData.getRecords());
//
//
//    }
}