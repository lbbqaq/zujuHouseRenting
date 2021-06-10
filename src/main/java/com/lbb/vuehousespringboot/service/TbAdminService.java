package com.lbb.vuehousespringboot.service;

import com.lbb.vuehousespringboot.entity.TbAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbb
 * @since 2021-02-28
 */
public interface TbAdminService extends IService<TbAdmin> {
    public List<TbAdmin> listById();
}
