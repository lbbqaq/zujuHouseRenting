package com.lbb.vuehousespringboot.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbOrder;
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
 * @since 2021-03-03
 */
public interface TbOrderMapper extends BaseMapper<TbOrder> {


    //    @Select("SELECT * FROM tb_tag t LEFT JOIN tb_house_tag ht ON t.`id`= ht.`tag_id` WHERE ht.`house_id`=#{houseId} ")
//   联表查询
    @Select("select * from tb_order where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="host_id",property="hostId"),
            @Result(column="host_id",property="houses",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            ),
            @Result(column="user_id",property="userId"),
            @Result(column="user_id",property="users",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
            @Result(column="host_id",property="host",
                    one=@One(  select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById")
            )
    })
    List<TbOrder> findAllOrderByHostId(Long hostId ,@Param("ew") QueryWrapper wrapper);



    @Select("select * from tb_order where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="host_id",property="hostId"),
            @Result(column="host_id",property="host",
                one=@One(  select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById")
            ),
            @Result(column="house_id",property="houseId"),
            @Result(column="house_id",property="houses",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            ),
            @Result(column="user_id",property="userId"),
            @Result(column="user_id",property="users",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
    })
    IPage <TbOrder> findAllOrder(Page<TbOrder> page, @Param("ew") QueryWrapper wrapper);

    @Select("select * from tb_order where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="host_id",property="hostId"),
            @Result(column="host_id",property="host",
                one=@One(  select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById")
            ),
            @Result(column="house_id",property="houseId"),
            @Result(column="house_id",property="houses",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            ),
            @Result(column="user_id",property="userId"),
            @Result(column="user_id",property="users",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
    })
    TbOrder findOrder(@Param("ew") QueryWrapper wrapper);


    @Select("select * from tb_order where ${ew.sqlSegment} ")
    @Results({
//          property 实体类字段    column（数据库字段）
            @Result(column="host_id",property="hostId"),
            @Result(column="host_id",property="host",
                    one=@One(  select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById")
            ),
            @Result(column="house_id",property="houseId"),
            @Result(column="house_id",property="houses",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper.findHouseByHouseId"
                    )
            ),
            @Result(column="user_id",property="userId"),
            @Result(column="user_id",property="users",
                    one=@One(
                            select="com.lbb.vuehousespringboot.mapper.TbUserMapper.getUserById"
                    )
            ),
    })
    List<TbOrder> findAllOrders(@Param("ew") QueryWrapper wrapper);
}
