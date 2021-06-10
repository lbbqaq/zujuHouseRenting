package com.lbb.vuehousespringboot.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordDto {


    @NotBlank(message = "id不能为空")
    private Long id;
    @NotBlank(message = "旧密码不能为空")
    private String oldPassword;
    @NotBlank(message = "新密码不能为空")
    private String newPassword;
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;


}
