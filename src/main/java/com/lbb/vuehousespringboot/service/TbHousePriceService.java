package com.lbb.vuehousespringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHousePrice;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbb
 * @since 2021-03-28
 */
public interface TbHousePriceService extends IService<TbHousePrice> {

    IPage findAllHousePriceById(Page<TbHousePrice> page , @Param("ew") QueryWrapper wrapper);

}
