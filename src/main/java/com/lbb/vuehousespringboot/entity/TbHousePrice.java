package com.lbb.vuehousespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.lbb.vuehousespringboot.service.TbHouseBaseService;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lbb
 * @since 2021-03-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbHousePrice implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 房屋id
     */
    private Long houseId;

    /**
     * 房屋现在价格
     */
    private Float houseNowPrice;

    /**
     * 期望价格
     */
    private Float houseExpectPrice;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户id
     */
    private Long hostId;

    /**
     * 状态
     */
    private Integer state;

    private LocalDateTime createTime;

    private String message;

    @TableField(exist = false)
    private TbHouseBase house;

    @TableField(exist = false)
    private TbUser user;

    @TableField(exist = false)
    private TbUser host;


}
