package com.lbb.vuehousespringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseWatch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbb
 * @since 2021-04-27
 */
public interface TbHouseWatchMapper extends BaseMapper<TbHouseWatch> {





    //    @Select("SELECT * FROM tb_tag t LEFT JOIN tb_house_tag ht ON t.`id`= ht.`tag_id` WHERE ht.`house_id`=#{houseId} ")
//   联表查询
    @Select("select * from tb_house_watch where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="house_id",property="houseId"),
            @Result(column="house_id",property="house",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            )
    })
    IPage<TbHouseWatch> findAllHouseWatch(Page<TbHouseWatch> page, @Param("ew") QueryWrapper wrapper);
}
