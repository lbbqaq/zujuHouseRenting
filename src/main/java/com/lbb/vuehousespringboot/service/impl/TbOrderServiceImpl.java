package com.lbb.vuehousespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbOrder;
import com.lbb.vuehousespringboot.mapper.TbOrderMapper;
import com.lbb.vuehousespringboot.service.TbOrderService;
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
 * @since 2021-03-03
 */
@Service
public class TbOrderServiceImpl extends ServiceImpl<TbOrderMapper, TbOrder> implements TbOrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Override
    public List<TbOrder> findAllOrderByHostId(Long hostId,QueryWrapper wrapper) {
        return tbOrderMapper.findAllOrderByHostId(hostId,wrapper);
    }


    @Override
    public IPage findAllOrder(Page<TbOrder> page, QueryWrapper wrapper) {
        return tbOrderMapper.findAllOrder(page,wrapper);
    }

    @Override
    public TbOrder findOrder(QueryWrapper wrapper) {
        return tbOrderMapper.findOrder(wrapper);
    }
}
