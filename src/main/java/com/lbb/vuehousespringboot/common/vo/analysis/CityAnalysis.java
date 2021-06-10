package com.lbb.vuehousespringboot.common.vo.analysis;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityAnalysis {

    List<String> createTimes;
    List<Float> prices;
}
