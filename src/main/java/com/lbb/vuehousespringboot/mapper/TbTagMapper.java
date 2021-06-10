package com.lbb.vuehousespringboot.mapper;

import com.lbb.vuehousespringboot.entity.TbTag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbb
 * @since 2021-03-01
 */
public interface TbTagMapper extends BaseMapper<TbTag> {


    @Select("SELECT * FROM tb_tag t \n" +
            "LEFT JOIN tb_house_tag ht ON ht.`tag_id`=t.`id`\n" +
            "WHERE ht.`house_id`=#{heroId}")
    List<TbTag> getListByHouseId(Long houseId);
}
