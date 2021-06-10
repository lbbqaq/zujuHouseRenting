package com.lbb.vuehousespringboot.service.impl;

import com.lbb.vuehousespringboot.entity.TbTag;
import com.lbb.vuehousespringboot.mapper.TbTagMapper;
import com.lbb.vuehousespringboot.service.TbTagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbb
 * @since 2021-03-01
 */
@Service
public class TbTagServiceImpl extends ServiceImpl<TbTagMapper, TbTag> implements TbTagService {

    @Autowired
    private  TbTagMapper tagMapper;

    //    根据字符串id获得tag列表
    @Override
    public List<TbTag> listTag(String ids) {
        System.out.println("1234"+ids);
        return tagMapper.selectBatchIds(covertToList(ids));
    }

    //    将字符串id转换成long类型链表
    private List<Long> covertToList(String ids){
        List<Long> list=new ArrayList<>();
        if(!"".equals(ids) && ids!=null  ){
            String[] idarry=ids.split(",");
            for(int i=0;i<idarry.length;i++){
                list.add(new Long(idarry[i]));
            }
        }
        return list;
    }
}
