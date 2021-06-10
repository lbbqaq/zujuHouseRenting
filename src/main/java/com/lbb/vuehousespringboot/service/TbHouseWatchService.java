package com.lbb.vuehousespringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHouseWatch;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  服务类
 * </p>
 *findAllHouseAndTag
 * @author lbb
 * @since 2021-04-27
 */
public interface TbHouseWatchService extends IService<TbHouseWatch> {

    IPage findAllHouseWatch(Page<TbHouseWatch> page, @Param("ew") QueryWrapper wrapper);
}
