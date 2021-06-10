package com.lbb.vuehousespringboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.common.vo.HouseCItyVO;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.mapper.TbHouseBaseMapper;
import com.lbb.vuehousespringboot.service.TbHouseBaseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lbb
 * @since 2021-01-27
 */
@Service
public class TbHouseBaseServiceImpl extends ServiceImpl<TbHouseBaseMapper, TbHouseBase> implements TbHouseBaseService {


    @Autowired
    private TbHouseBaseMapper tbHouseBaseMapper;


    @Override
    public IPage findAllHouseAndTag(Page<TbHouseBase> page, QueryWrapper wrapper) {
        return tbHouseBaseMapper.findAllHouseAndTag(page, wrapper);
    }

    @Override
    public TbHouseBase findAllHouseAndTagById(QueryWrapper wrapper) {
        return tbHouseBaseMapper.findAllHouseAndTagById(wrapper);
    }

    @Override
    public List<HouseCItyVO> findAllHouseCity() {
        List<TbHouseBase> list = tbHouseBaseMapper.selectList(new QueryWrapper<>());
        List<HouseCItyVO> list1 = new ArrayList<>();
        list1.add(new HouseCItyVO(-1, "不限"));
        HashSet set = new HashSet<String>();
// 去重复
        for (int i = 0; i < list.size(); i++) {
            set.add(list.get(i).getHouseCity());
            System.out.println(set);
        }
        Iterator it = set.iterator();
        int i = 0;
//存入列表
        while (it.hasNext()) {
            HouseCItyVO houseCItyVO = new HouseCItyVO();
            houseCItyVO.setId(i);
            i++;
            houseCItyVO.setCity((String) it.next());
            list1.add(houseCItyVO);
        }
//  存入-1值

        System.out.println(list1);
        return list1;
    }
}
