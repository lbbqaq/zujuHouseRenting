package com.lbb.vuehousespringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHousePrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lbb
 * @since 2021-03-28
 */
public interface TbHousePriceMapper extends BaseMapper<TbHousePrice> {

    @Select("SELECT * FROM tb_house_price  where ${ew.sqlSegment}")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="house_id",property="houseId"),
//            @Result(column="house_id",property="tags",
//                    many=@Many(
//                            select="com.lbb.vuehousespringboot.mapper.TbTagMapper.getListByHouseId"
//                    )
//            ),
            @Result(column="user_id",property="userId"),
            @Result(column="user_id",property="user",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
            @Result(column="host_id",property="hostId"),
            @Result(column="host_id",property="host",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
            @Result(column="house_id",property="houseId"),
            @Result(column="house_id",property="house",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            )
    })
    IPage<TbHousePrice> findAllHousePriceById(Page page, @Param("ew") QueryWrapper wrapper);

}
