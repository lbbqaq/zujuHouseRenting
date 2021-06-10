package com.lbb.vuehousespringboot.controller;


import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHousePrice;
import com.lbb.vuehousespringboot.service.TbHouseBaseService;
import com.lbb.vuehousespringboot.service.TbHousePriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-03-28
 */
@RestController
@RequestMapping("/tb-house-price")
public class TbHousePriceController {

    @Autowired
    private TbHousePriceService tbHousePriceService;
    @Autowired
    private TbHouseBaseService tbHouseBaseService;

    @PostMapping("/updateHousePrice")
    public Result updateHousePrice(@RequestBody TbHousePrice tbHousePrice){
        System.out.println(tbHousePrice);
        boolean save = tbHousePriceService.save(tbHousePrice);
        return Result.succful(save);
    }

    @GetMapping("/updateHousePriceByState")
    public Result updateHousePriceByState(@RequestParam Long id,@RequestParam Integer state){
        TbHousePrice housePrice = tbHousePriceService.getById(id);
        housePrice.setState(state);
        boolean save = tbHousePriceService.saveOrUpdate(housePrice);
        if (state == 2) {
            TbHouseBase houseBase = tbHouseBaseService.getById(housePrice.getHouseId());
            houseBase.setHouseRentalPrice(housePrice.getHouseExpectPrice());
            tbHouseBaseService.saveOrUpdate(houseBase);
        }
        return Result.succful(save);
    }

    @GetMapping("/findHousePriceList")
    public Result findHousePriceList(@RequestParam Integer currentPage){
        System.out.println("/findHousePriceList");
        Page  page =new Page(currentPage,5);
        IPage<TbHousePrice> pageData = tbHousePriceService.findAllHousePriceById(page, new QueryWrapper<TbHousePrice>()
        .eq("1",1));
        return Result.succful(pageData);
    }

    @GetMapping("/findAllHousePriceByUserId")
    public Result findAllHousePriceByUserId(@RequestParam Long userId,@RequestParam(defaultValue = "1") Integer currentPage) {
        System.out.println("/tb_house_base/getAllHousePrice");
        Page page = new Page(currentPage, 5);
        IPage<TbHousePrice> pageData = tbHousePriceService.findAllHousePriceById(page, new QueryWrapper<TbHousePrice>()
                .eq("1", 1).eq("user_id",userId));
        System.out.println(pageData.getRecords());
        return Result.succful(pageData);
    }

}
