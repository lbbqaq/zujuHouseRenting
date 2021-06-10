package com.lbb.vuehousespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbb
 * @since 2021-03-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 房子id
     */
    private Long houseId;

    /**
     * 房主id
     */
    private Long hostId;

    /**
     * 有效时间
     */
    private LocalDateTime validTime;

    /**
     * 总价值
     */
    private Float totalPrice;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 选择月份
     */
    private Integer timeSelect;

    /**
     *完成时间
     */
    private LocalDateTime overTime;
    /**
     * 退租状态，0-无，1-申请退租，2-退租成功，3-退租失败
     */
    private Integer overState;

    /**
     * 租客信息
     */
    @TableField(exist = false)
    private TbUser users;

    /**
     * 房源信息
     */
    @TableField(exist = false)
    private TbHouseBase houses;


    /**
     * 房主信息
     */
    @TableField(exist = false)
    private TbUser host;


    //查询的时候不显示该字段
    @TableField(select = false)
    @TableLogic
    private Integer deleted;


}
