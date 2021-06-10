package com.lbb.vuehousespringboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.entity.TbTag;
import com.lbb.vuehousespringboot.service.TbTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-03-01
 */
@RestController
@RequestMapping("/tb-tag")
public class TbTagController {

    @Autowired
    private TbTagService tbTagService;

    @GetMapping("/getAllTag")
    public Result getAllTag(){
        return Result.succful( tbTagService.list());
    }

    @GetMapping("/getAllTagByPage")
    public Result getAllTag(@RequestParam(defaultValue = "1") Integer currentPage){
        Page page= new Page(currentPage,5);
        IPage page1 = tbTagService.page(page,new QueryWrapper<>());
        return Result.succful(page1);
    }

    @PostMapping("/updateTag")
    public Result updateTag(@RequestBody TbTag tbTag){
        System.out.println(tbTag);
        if(tbTag.getId()==null){
            TbTag b = tbTagService.getOne(new QueryWrapper<TbTag>().eq("tag_name",tbTag.getTagName()));
            System.out.println(b);
            if(b!=null){
                return Result.fail("标签已存在，添加失败！");
            }
            tbTagService.saveOrUpdate(tbTag);
        }
        boolean b = tbTagService.saveOrUpdate(tbTag);
        return Result.succful(b);
    }

    @GetMapping("/deleteTag")
    public Result deleteTag(@RequestParam Long id){
        boolean b = tbTagService.removeById(id);
        return Result.succful(b);
    }
//    @GetMapping("/saveTag")
//    public Result updateTag(){
//        return Result.succful(tbTagService.list());
//    }



}
