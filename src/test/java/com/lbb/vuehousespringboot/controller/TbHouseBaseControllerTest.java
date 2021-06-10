package com.lbb.vuehousespringboot.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper;
import com.lbb.vuehousespringboot.service.TbHouseBaseService;
import com.lbb.vuehousespringboot.service.impl.TbHouseBaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TbHouseBaseControllerTest {

    @Autowired
    TbHouseBaseMapper mapper;

    @Autowired
    TbHouseBaseService tbHouseBaseService;

    @Test
    public void selectAll(){
        QueryWrapper<TbHouseBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("house_id");
        List<TbHouseBase> tbHouseBases = mapper.selectList(queryWrapper);
        System.out.println(tbHouseBases);

    }

    @Test
    public void selectAllFromPage(){
        Page page=new Page(1,3);
        IPage pageData = tbHouseBaseService.page(page,
                new QueryWrapper<TbHouseBase>().orderByDesc("house_update_time"));
        System.out.println(pageData.getRecords());
    }

}