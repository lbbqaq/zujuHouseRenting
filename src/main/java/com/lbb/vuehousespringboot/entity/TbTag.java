package com.lbb.vuehousespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lbb.vuehousespringboot.mapper.TbHouseTagMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.ManyToMany;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbb
 * @since 2021-03-01
 */
@Data

@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)

public class TbTag implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String tagName;

    private String tagDescribe;

    //查询的时候不显示该字段
    @TableField(select = false)
    @TableLogic
    private Integer deleted;


}
