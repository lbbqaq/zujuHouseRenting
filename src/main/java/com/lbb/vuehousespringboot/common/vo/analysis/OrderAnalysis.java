package com.lbb.vuehousespringboot.common.vo.analysis;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
public class OrderAnalysis {

    private List<String> citys;
    private List<Integer> cityNumber;
    private List<Float> cityPrices;
    private LocalDateTime[] dateTimes;

}
