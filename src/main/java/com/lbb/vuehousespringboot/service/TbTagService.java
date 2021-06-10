package com.lbb.vuehousespringboot.service;

import com.lbb.vuehousespringboot.entity.TbTag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lbb
 * @since 2021-03-01
 */
public interface TbTagService extends IService<TbTag> {

    public List<TbTag> listTag(String ids);

}
