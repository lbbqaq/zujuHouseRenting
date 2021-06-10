package com.lbb.vuehousespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseWatch;
import com.lbb.vuehousespringboot.mapper.TbHouseWatchMapper;
import com.lbb.vuehousespringboot.service.TbHouseWatchService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbb
 * @since 2021-04-27
 */
@Service
public class TbHouseWatchServiceImpl extends ServiceImpl<TbHouseWatchMapper, TbHouseWatch> implements TbHouseWatchService {

    @Autowired
    private TbHouseWatchMapper tbHouseWatchMapper;

    @Override
    public IPage findAllHouseWatch(Page<TbHouseWatch> page, QueryWrapper wrapper) {

        return  tbHouseWatchMapper.findAllHouseWatch(page,wrapper);
    }
}
