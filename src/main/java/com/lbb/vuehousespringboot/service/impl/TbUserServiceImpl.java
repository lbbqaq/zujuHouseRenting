package com.lbb.vuehousespringboot.service.impl;

import com.lbb.vuehousespringboot.entity.TbUser;
import com.lbb.vuehousespringboot.mapper.TbUserMapper;
import com.lbb.vuehousespringboot.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbb
 * @since 2020-12-13
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

}
