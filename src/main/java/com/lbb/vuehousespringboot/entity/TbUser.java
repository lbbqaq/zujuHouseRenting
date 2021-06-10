package com.lbb.vuehousespringboot.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbb
 * @since 2020-12-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class TbUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime created;

    /**
     * 最后一次登录
     */
    private LocalDateTime lastLogin;
    /**
     * 权限
     */
    private Integer power;
    /**
     * 电话
     */
    private Long phone;

    /**
     * 身份证
     */ 
    private Long idCard;
    /**
     * 真实名字
     */
    private String realName;
   /**
     * 性别
     */
    private String sex;
    /**
     * 是否删除
     */
    //查询的时候不显示该字段
    @TableField(select = false)
    @TableLogic
    private Integer deleted;




}
