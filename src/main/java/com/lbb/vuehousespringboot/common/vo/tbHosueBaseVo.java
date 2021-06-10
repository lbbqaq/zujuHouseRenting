package com.lbb.vuehousespringboot.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class tbHosueBaseVo implements Serializable {
    private Long houseId;
    private String houseTitle;
    private String houseHostId;
    private String houseRentalArea;
}
