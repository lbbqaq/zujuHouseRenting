package com.lbb.vuehousespringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbb
 * @since 2021-03-03
 */
public interface TbOrderService extends IService<TbOrder> {

    List<TbOrder> findAllOrderByHostId(Long hostId, @Param("ew") QueryWrapper wrapper);


    IPage<TbOrder> findAllOrder(Page<TbOrder> page, @Param("ew") QueryWrapper wrapper);


    TbOrder findOrder(@Param("ew") QueryWrapper wrapper);
}
