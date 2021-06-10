package com.lbb.vuehousespringboot.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterDto implements Serializable {

    List<Integer> watchFilterPrice;
    String watchFilterRentType;
    Integer  watchFliterOrientations;
    String watchFilterCity;
    String keyWord;
    Integer currentPage;
    String orderFilter;
    Integer stateFilter;

    public Integer getCurrentPage() {
        return currentPage==null?1:currentPage;
    }
}
