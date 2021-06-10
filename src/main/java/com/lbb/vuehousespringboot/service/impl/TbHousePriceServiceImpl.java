package com.lbb.vuehousespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHousePrice;
import com.lbb.vuehousespringboot.mapper.TbHousePriceMapper;
import com.lbb.vuehousespringboot.service.TbHousePriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbb
 * @since 2021-03-28
 */
@Service
public class TbHousePriceServiceImpl extends ServiceImpl<TbHousePriceMapper, TbHousePrice> implements TbHousePriceService {

    @Autowired
    private TbHousePriceMapper tbHousePriceMapper;

    @Override
    public IPage findAllHousePriceById(Page<TbHousePrice> page, QueryWrapper wrapper) {
        return tbHousePriceMapper.findAllHousePriceById(page,wrapper);
    }
}
