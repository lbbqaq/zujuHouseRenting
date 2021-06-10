package com.lbb.vuehousespringboot.controller;

import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.common.vo.analysis.CityAnalysis;
import com.lbb.vuehousespringboot.common.vo.analysis.OrderAnalysis;
import com.lbb.vuehousespringboot.service.AnalysisService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analysis")
public class AnalysisController {


    @Autowired
    private AnalysisService analysisService;

    @GetMapping("/OrderAnalysis")
    public Result OrderAnalysis(){
        OrderAnalysis orderAnalysis = analysisService.getOrderAnalysisCity();
        return Result.succful(orderAnalysis);
    }

    @GetMapping("/CityAnalysisDetail")
    public Result CityAnalysisDetail(@RequestParam("cityName") String cityName){
        System.out.println("/analysis/CityAnalysisDetail");
        CityAnalysis cityDetailAnalysis = analysisService.getCityDetailAnalysis(cityName);
        System.out.println(cityDetailAnalysis);
        return Result.succful(cityDetailAnalysis);
    }

}
