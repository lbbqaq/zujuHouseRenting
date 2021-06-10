package com.lbb.vuehousespringboot.common.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PublishDto implements Serializable {



    @NotBlank(message = "城市不能为空")
    private String houseCity;
    @NotBlank(message = "区县不能为空")
    private String houseDistrict;
    @NotBlank(message = "小区不能为空")
    private String houseVillage;
    @NotBlank(message = "门牌号不能为空")
    private String houseNumber;
    @NotBlank(message = "布局不能为空")
    private String houseLayout;
    @NotBlank(message = "预售价格不能为空")
    private String houseRentalPrice;
    @NotBlank(message = "出租方式不能为空")
    private String houseRentalMethod;
    @NotBlank(message = "支付方式不能为空")
    private String housePaymentMethod;

    private Long houseHostId;
//    private String hostPhone;



}
