package com.lbb.vuehousespringboot.common.dto;

import lombok.Data;

@Data
public class UserFilterDto {

    Integer currentPage;
    Integer filterState;
    String keyword;

}
