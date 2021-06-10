package com.lbb.vuehousespringboot.service.impl;

import com.lbb.vuehousespringboot.entity.TbAdmin;
import com.lbb.vuehousespringboot.mapper.TbAdminMapper;
import com.lbb.vuehousespringboot.service.TbAdminService;
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
 * @since 2021-02-28
 */
@Service
public class TbAdminServiceImpl extends ServiceImpl<TbAdminMapper, TbAdmin> implements TbAdminService {

    @Autowired
    private TbAdminMapper adminMapper;

    @Override
    public List<TbAdmin> listById() {
        return null;
    }
}
