package com.lbb.vuehousespringboot.mapper;

import com.lbb.vuehousespringboot.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbb
 * @since 2020-12-13
 */
public interface TbUserMapper extends BaseMapper<TbUser> {

    @Select("SELECT * FROM tb_user where id=#{userId} ")
    TbUser getUserById(Long userId);
}
