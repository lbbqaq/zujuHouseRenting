package com.lbb.vuehousespringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbHouseWatch;
import com.lbb.vuehousespringboot.service.TbHouseWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-04-27
 */
@RestController
@RequestMapping("/tb-house-watch")
public class TbHouseWatchController {

    @Autowired
    private TbHouseWatchService tbHouseWatchService;

    @GetMapping("/updateWatch")
    Result updateWathc(@RequestParam("userId") Long userId,@RequestParam("houseId") Long houseId){
//        TbHouseWatch tbHouseWatch=new TbHouseWatch();
        TbHouseWatch tbHouseWatch = tbHouseWatchService.getOne(new QueryWrapper<TbHouseWatch>().eq("user_id", userId).eq("house_id", houseId));
        if(tbHouseWatch!=null){
            tbHouseWatch.setNumber(tbHouseWatch.getNumber()+1);
            tbHouseWatch.setUpdateTime(LocalDateTime.now());
            tbHouseWatchService.saveOrUpdate(tbHouseWatch);
        }
        else{
            TbHouseWatch tbHouseWatch2 =new TbHouseWatch();
            tbHouseWatch2.setHouseId(houseId);
            tbHouseWatch2.setUserId(userId);
            tbHouseWatch2.setNumber((long)1);
            tbHouseWatch2.setUpdateTime(LocalDateTime.now());
            tbHouseWatchService.saveOrUpdate(tbHouseWatch2);
        }
        return Result.succful("");
    }



    @GetMapping("/getAllHouseWatch")
    public Result getAllHouseWatch(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam("userId") Long userId) {
        System.out.println("/tb-house-watch/getAllHouseWatch");
        Page page = new Page(currentPage, 5);
        IPage<TbHouseWatch> pageData = tbHouseWatchService.findAllHouseWatch(page, new QueryWrapper<TbHouseWatch>()
                .eq("1", 1).eq("user_id",userId).orderByDesc("update_time"));
        System.out.println(pageData.getRecords());
        return Result.succful(pageData);
    }
}
