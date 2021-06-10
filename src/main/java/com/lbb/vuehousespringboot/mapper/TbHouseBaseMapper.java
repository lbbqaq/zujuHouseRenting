package com.lbb.vuehousespringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lbb.vuehousespringboot.entity.TbTag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbb
 * @since 2021-01-27
 */
public interface TbHouseBaseMapper extends BaseMapper<TbHouseBase> {


//    @Select("SELECT * FROM tb_tag t LEFT JOIN tb_house_tag ht ON t.`id`= ht.`tag_id` WHERE ht.`house_id`=#{houseId} ")
//   联表查询
    @Select("select * from tb_house_base where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="house_id",property="houseId"),
//            @Result(column="house_id",property="tags",
//                    many=@Many(
//                            select="com.lbb.vuehousespringboot.mapper.TbTagMapper.getListByHouseId"
//                    )
//            ),
            @Result(column="house_host_id",property="houseHostId"),
            @Result(column="house_host_id",property="user",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            )
    })
    IPage<TbHouseBase> findAllHouseAndTag(Page<TbHouseBase> page, @Param("ew") QueryWrapper wrapper);

    @Select("SELECT * FROM tb_house_base  where ${ew.sqlSegment}")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="house_id",property="houseId"),
//            @Result(column="house_id",property="tags",
//                    many=@Many(
//                            select="com.lbb.vuehousespringboot.mapper.TbTagMapper.getListByHouseId"
//                    )
//            ),
            @Result(column="house_host_id",property="houseHostId"),
            @Result(column="house_host_id",property="user",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            )
    })
    TbHouseBase findAllHouseAndTagById(@Param("ew")QueryWrapper wrapper);

    @Select("select * from tb_house_base  where house_host_id=#{hostId}")
    TbHouseBase findHouseByHostId(Long hostId);

    @Select("select * from tb_house_base  where house_id=#{houseId}")
    TbHouseBase findHouseByHouseId(Long houseId);

}
