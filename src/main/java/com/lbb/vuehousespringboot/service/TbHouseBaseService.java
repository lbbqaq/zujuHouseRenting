package com.lbb.vuehousespringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.vo.HouseCItyVO;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbb
 * @since 2021-01-27
 */
public interface TbHouseBaseService extends IService<TbHouseBase> {

    IPage findAllHouseAndTag(Page<TbHouseBase> page, @Param("ew") QueryWrapper wrapper);

    TbHouseBase findAllHouseAndTagById(@Param("ew") QueryWrapper wrapper);

    List<HouseCItyVO> findAllHouseCity();


}
