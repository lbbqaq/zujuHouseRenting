package com.lbb.vuehousespringboot.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.dto.FilterDto;
import com.lbb.vuehousespringboot.common.dto.PublishDto;
import com.lbb.vuehousespringboot.common.lang.Result;
import com.lbb.vuehousespringboot.common.vo.HouseCItyVO;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbHouseTag;
import com.lbb.vuehousespringboot.entity.TbTag;
import com.lbb.vuehousespringboot.service.*;
import com.lbb.vuehousespringboot.service.impl.TbTagServiceImpl;
import com.lbb.vuehousespringboot.util.EmailTool;
import com.lbb.vuehousespringboot.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lbb
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/tb-house-base")
public class TbHouseBaseController {

    @Autowired
    TbHouseBaseService tbHouseBaseService;
    @Autowired
    TbTagService tbTagService;
    @Autowired
    TbOrderService tbOrderService;
    @Autowired
    TbHouseTagService tbHouseTagService;
    @Autowired
    ReadXLS readXLS;

    @Autowired
    EmailTool emailTool;

//    @GetMapping("/getHotHouse")
//    public Result GetHotHouse() {
//        Page page = new Page(1, 4);
//        IPage<TbHouseBase> pageData = tbHouseBaseService.page(page, new QueryWrapper<TbHouseBase>()
//                .eq("house_state", 1)
//                .orderByDesc("create_time")
//                .isNotNull("create_time")
//                .eq("deleted", "0"));
//        System.out.println("123" + pageData.getRecords().get(0).getHouseTag());
//        for (int i = 0; i < pageData.getRecords().size(); i++) {
//            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
//        }
//        return Result.succful(MapUtil.builder()
//                .put("list", pageData)
//                .map()
//        );
//    }

    @GetMapping("/findHotHouse")
    public Result findHotHouse(@RequestParam("tag") Integer tag) {
        Page page = new Page(1, 4);
        QueryWrapper qw= new QueryWrapper<TbHouseBase>()
                .eq("house_state", 1)
                .eq("deleted", "0");
        qw.apply( tag != null,"FIND_IN_SET ('"+ tag +"', house_tag )");
        IPage<TbHouseBase> pageData = tbHouseBaseService.page(page, qw);
        for (int i = 0; i < pageData.getRecords().size(); i++) {
            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
        }
        return Result.succful(MapUtil.builder()
                .put("list", pageData)
                .map()
        );
    }

    @GetMapping("/getAllHouseBystate")
    public Result GetAllHouseBystate(@RequestParam(defaultValue = "1") Integer currentPage) {
        System.out.println("/tb_house_base/GetAllHouseBystate");
        Page page = new Page(currentPage, 5);
        IPage<TbHouseBase> pageData = tbHouseBaseService.findAllHouseAndTag(page, new QueryWrapper<TbHouseBase>()
                .eq("house_state", 1).eq("1", 1).orderByAsc("house_id")
                .eq("deleted", "0"));
        System.out.println(pageData.getRecords());
//        获得Tag
        for (int i = 0; i < pageData.getRecords().size(); i++) {
            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
        }
        return Result.succful(MapUtil.builder()
                .put("list", pageData)
                .map()
        );
    }

    @GetMapping("/getAllHouse")
    public Result getAllHouse(@RequestParam(defaultValue = "1") Integer currentPage) {
        System.out.println("/tb_house_base/getAllHouse");
        Page page = new Page(currentPage, 5);
        IPage<TbHouseBase> pageData = tbHouseBaseService.findAllHouseAndTag(page, new QueryWrapper<TbHouseBase>()
                .eq("1", 1).orderByDesc("create_time")
                .eq("deleted", "0"));
        System.out.println(pageData.getRecords());
        for (int i = 0; i < pageData.getRecords().size(); i++) {
//            pageData.getRecords().get(i).setTags(tbTagService.listTag(pageData.getRecords().get(i).getHouseTag()));

            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
        }
        return Result.succful(MapUtil.builder()
                .put("list", pageData)
                .map()
        );
    }


    @GetMapping("/findAllHouseCity")
    public Result findAllHouseCity() {
        List<HouseCItyVO> allHouseCity = tbHouseBaseService.findAllHouseCity();
        return Result.succful(allHouseCity);
    }


    @PostMapping("/editHouse")
    public Result editHouse(@Validated @RequestBody TbHouseBase house) {
        System.out.println("/editHouse");
        System.out.println(house);
        TbHouseBase temp = null;
        //BeanUtil.copyProperties(temp,house,"houseId");
        List<TbTag> tags = house.getTags();
        String houseTag = null;
        if (house.getTags() != null) {
            houseTag = "";
            System.out.println(house.getTags());
            for (TbTag tag : tags) {
                houseTag = houseTag + tag.getId() + ',';
            }
            house.setHouseTag(houseTag);
            List<TbHouseTag> tbHouseTags = new ArrayList<>();
            System.out.println(house);
            System.out.println(house.getTags());
            if (tags != null) {
                for (TbTag tbTag : tags) {
                    TbHouseTag tbHouseTag = new TbHouseTag();
                    tbHouseTag.setHouseId(house.getHouseId());
                    tbHouseTag.setTagId(tbTag.getId());
                    tbHouseTagService.save(tbHouseTag);
                    tbHouseTags.add(tbHouseTag);
                }
            }
        }

        house.setHouseAddress(house.getHouseCity() + "-" + house.getHouseDistrict() + "-" + house.getHouseVillage());

        tbHouseBaseService.saveOrUpdate(house);

//        System.out.println(tbHouseTags);
//        tbHouseTagService.save(tbHouseTags);
        return Result.succful(null);
    }

    @PostMapping("/publish")
    public Result publish(@Validated @RequestBody PublishDto publishDto) throws FileNotFoundException {
        System.out.println("/publish");
        TbHouseBase temp = new TbHouseBase();
        System.out.println(publishDto);
        temp.setCreateTime(LocalDateTime.now());
        temp.setHouseUpdateTime(LocalDateTime.now());
        Short state = 0;
        temp.setHouseState(state);
        BeanUtil.copyProperties(publishDto, temp);
        temp.setHouseAddress(publishDto.getHouseCity() + "-" + publishDto.getHouseDistrict() + "-" + publishDto.getHouseVillage());
        System.out.println(temp);

        tbHouseBaseService.save(temp);
        return Result.succful("发布成功，正在审核！");
    }

    @GetMapping("/findHouseByIdAndState")
    public Result findHouseByIdAndState(@RequestParam Long houseId) {
        System.out.println("findHouseById");
        Assert.notNull(houseId, "房源不存在");
        QueryWrapper<TbHouseBase> qw = new QueryWrapper<TbHouseBase>()
                .eq("house_state", 1).eq("house_id", houseId)
                .orderByDesc("create_time");
        TbHouseBase houseBase = tbHouseBaseService.findAllHouseAndTagById(qw);
//获取tag
        houseBase = findTag(houseBase);

//
//        System.out.println(houseBase.getHousePicRealAddress());
//        System.out.println(houseBase);
        Assert.notNull(houseBase, "房源不存在");
        return Result.succful(houseBase);
    }

    public TbHouseBase findTag(TbHouseBase tbHouseBase) {
        if (tbHouseBase.getHouseTag() != null || StringUtils.isNotEmpty(tbHouseBase.getHouseTag())) {
            System.out.println(tbHouseBase.getHouseTag());
            TbHouseBase houseBase = tbHouseBase.setTags(tbTagService.listTag(tbHouseBase.getHouseTag()));
            return houseBase;
        } else {
            return tbHouseBase;
        }
    }


    @GetMapping("/findHouseById")
    public Result findHouseById(@RequestParam Long houseId) {
        System.out.println("findHouseById");
        Assert.notNull(houseId, "房源不存在");
        QueryWrapper<TbHouseBase> qw = new QueryWrapper<TbHouseBase>()
                .eq("house_id", houseId)
                .eq("deleted", "0");
        TbHouseBase houseBase = tbHouseBaseService.findAllHouseAndTagById(qw);
        if (houseBase.getHouseTag() != null) {
            houseBase.setTags(tbTagService.listTag(houseBase.getHouseTag()));
        }
        System.out.println(houseBase.getHousePicRealAddress());
        System.out.println(houseBase);
        Assert.notNull(houseBase, "房源不存在");
        return Result.succful(houseBase);
    }


    @GetMapping("/findHouseStateById")
    public Result findHouseStateById(@RequestParam Long houseId) {
        TbHouseBase houseBase = tbHouseBaseService.getById(houseId);
        Assert.notNull(houseBase, "房源不存在");
        if (houseBase.getHouseState() == 2) {
            return Result.fail("房源已被抢先预定");
        } else if (houseBase.getHouseState() == 1) {
            return Result.succful(houseBase);
        }
        return Result.fail("发生错误");
    }

    @PostMapping("/succReserveHouse")
    public Result succReserveHouse(@RequestParam Long houseId) {
        TbHouseBase houseBase = tbHouseBaseService.getById(houseId);
        Assert.notNull(houseBase, "房源不存在");
        if (houseBase.getHouseState() == 2) {
            return Result.fail("房源已被抢先预定");
        } else if (houseBase.getHouseState() == 1) {
            return Result.succful(houseBase);
        } else {
            return Result.fail("发生错误");
        }
    }

    @PostMapping("/checkHouseState")
    public Result checkHouseState() {
//        tbOrderService.getById()
        tbOrderService.update();
        return Result.succful("");
    }

    @GetMapping("/findHouseListByHostId/{hostId}/{currentPage}")
    public Result findHouseListByHostId(@PathVariable Long hostId, @PathVariable Integer currentPage) {
        Page page = new Page<>(currentPage, 5);
        IPage<TbHouseBase> pageData = tbHouseBaseService.findAllHouseAndTag(page, new QueryWrapper<TbHouseBase>()
                .eq("house_host_id", hostId)
                .eq("deleted", "0"));
        for (int i = 0; i < pageData.getRecords().size(); i++) {
            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
        }
        return Result.succful(pageData);
    }

    @GetMapping("/getHouseListByState")
    public Result getHouseListByState(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 5);
        IPage pageData = tbHouseBaseService.findAllHouseAndTag(page, new QueryWrapper<TbHouseBase>()
                .eq("house_state", 0).eq("deleted", "0"));
        return Result.succful(pageData);
    }

//    @GetMapping("/getHouseListByState")
//    public Result getHouseListByFilter(@RequestParam(defaultValue = "1") Integer currentPage) {
////        tbOrderService.getById()
////        找出未审核的房源
//        Page page = new Page(currentPage, 5);
//        IPage pageData = tbHouseBaseService.findAllHouseTag(page,new QueryWrapper<TbHouseBase>()
//                .eq("house_state", 0));
//        return Result.succful(pageData);
//    }

    @PostMapping("/getHouseListByFilter")
    public Result getHouseListByFilter(
            @RequestBody FilterDto filterDto
    ) {
        System.out.println(filterDto);
        QueryWrapper<TbHouseBase> qw = new QueryWrapper<>();
        qw.eq("1", 1).eq("deleted", "0");

        if (filterDto.getStateFilter() != null) {
            qw.eq("house_state", filterDto.getStateFilter());
        }

        if (!filterDto.getWatchFilterCity().equals("不限")) {
            qw.eq("house_city", filterDto.getWatchFilterCity());
        }

//        List<Integer> list23=filterDto.getWatchFilterPrice().stream().sorted().collect(Collectors.toList());
        Collections.sort(filterDto.getWatchFilterPrice());
//        Collections.sort(filterDto.getWatchFliterOrientations());
        System.out.println(filterDto.getWatchFilterPrice());
//        System.out.println(filterDto.getWatchFliterOrientations());
        for (Integer str : filterDto.getWatchFilterPrice()) {
            switch (str) {
                case 1:
                    qw.le("house_rental_price", 1000);
                    qw.or();
                    break;
                case 2:
                    qw.between("house_rental_price", 1000, 1500);
                    qw.or();
                    break;
                case 3:
                    qw.between("house_rental_price", 1500, 2000);
                    qw.or();
                    break;
                case 4:
                    qw.between("house_rental_price", 2000, 3000);
                    qw.or();
                    break;
                case 5:
                    qw.between("house_rental_price", 3000, 5000);
                    qw.or();
                    break;
                case 6:
                    qw.ge("house_rental_price", 5000);
                    qw.or();
                    break;
                case -1:
                    qw.ge("house_rental_price", 0);
                    qw.or();
                    break;
                default:
                    break;
            }
        }
        switch (filterDto.getWatchFilterRentType()) {
            case "1":
                qw.eq("house_rental_method", "整租");
                break;
            case "2":
                qw.eq("house_rental_method", "合租");
                break;
            case "-1":
                break;
            default:
                break;
        }

//        for (Integer str : ) {
        switch (filterDto.getWatchFliterOrientations()) {
            case 1:
                qw.eq("house_orientation", "东");
                qw.or();
                break;
            case 2:
                qw.eq("house_orientation", "南");
                qw.or();
                break;
            case 3:
                qw.eq("house_orientation", "西");
                qw.or();
                break;
            case 4:
                qw.eq("house_orientation", "北");
                qw.or();
                break;
            case 5:
                qw.eq("house_orientation", "东北");
                qw.or();
                break;
            case -1:
                qw.like("house_orientation", "");
                break;
            default:
                break;
        }
//        }


//        switch (filterDto.getWatchFilterCity()) {
//            case "不限":
//                qw.eq("house_city", "不限");
//                break;
//            case "广州":
//                qw.eq("house_city", "广州");
//                break;
//            case "茂名":
//                qw.eq("house_city", "茂名");
//                break;
//            case "深圳":
//                qw.eq("house_city", "深圳");
//                break;
//            default:
//                break;
//        }


//        keyword
        System.out.println(filterDto.getKeyWord());
        if (!filterDto.getKeyWord().equals("")) {
            qw.like("house_village", filterDto.getKeyWord())
                    .or()
                    .like("house_address", filterDto.getKeyWord())
                    .or()
                    .like("house_district", filterDto.getKeyWord())
            ;
        }

        //orderFilter
        if (filterDto.getOrderFilter() != "") {
            switch (filterDto.getOrderFilter()) {
                case "1":
                    qw.orderByDesc("house_rental_price");
                    break;
                case "2":
                    qw.orderByDesc("house_rental_area");
                    break;
                default:
                    break;
            }
        }


        Page page = new Page(filterDto.getCurrentPage(), 5);
        IPage<TbHouseBase> pageData = tbHouseBaseService.findAllHouseAndTag(page, qw);

        for (int i = 0; i < pageData.getRecords().size(); i++) {
            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
        }
        System.out.println(filterDto);
        return Result.succful(pageData);
    }
//    @PostMapping("/getHouseListByAdmin")
//    public Result getHouseListByAdmin(
//            @RequestBody FilterDto filterDto) {
//        System.out.println(filterDto);
//        QueryWrapper<TbHouseBase> qw = new QueryWrapper<>();
//        qw.eq("1", 1).eq("deleted", "0").ne("hosue_state",0);
//        if (!filterDto.getWatchFilterCity().equals("不限")) {
//            qw.eq("house_city", filterDto.getWatchFilterCity());
//        }
//
//
//
////        switch (filterDto.getWatchFilterCity()) {
////            case "不限":
////                qw.eq("house_city", "不限");
////                break;
////            case "广州":
////                qw.eq("house_city", "广州");
////                break;
////            case "茂名":
////                qw.eq("house_city", "茂名");
////                break;
////            case "深圳":
////                qw.eq("house_city", "深圳");
////                break;
////            default:
////                break;
////        }
//
//
////        keyword
//        System.out.println(filterDto.getKeyWord());
//        if (!filterDto.getKeyWord().equals("")) {
//            qw.like("house_village", filterDto.getKeyWord())
//                    .or()
//                    .like("house_address", filterDto.getKeyWord())
//                    .or()
//                    .like("house_district", filterDto.getKeyWord())
//            ;
//        }
//
//        //orderFilter
//        if (filterDto.getOrderFilter() != "") {
//            switch (filterDto.getOrderFilter()) {
//                case "1":
//                    qw.orderByDesc("house_rental_price");
//                    break;
//                case "2":
//                    qw.orderByDesc("house_rental_area");
//                    break;
//                default:
//                    break;
//            }
//        }
//        if(filterDto.getStateFilter()!=null){
//            qw.eq("house_state",filterDto.getStateFilter());
//        }
//
//
//        Page page = new Page(filterDto.getCurrentPage(), 5);
//        IPage<TbHouseBase> pageData = tbHouseBaseService.findAllHouseAndTag(page, qw);
//
//        for (int i = 0; i < pageData.getRecords().size(); i++) {
//            pageData.getRecords().set(i, findTag(pageData.getRecords().get(i)));
//        }
//        System.out.println(filterDto);
//        return Result.succful(pageData);
//    }


    @PostMapping("/uploadFile")
    public Result uploadHousePicture(@RequestParam("files") MultipartFile[] reportFile, @RequestParam("houseId") Long houseId) throws FileNotFoundException {
        System.out.println(reportFile);
        //文件上传的地址
        for (MultipartFile multipartFile : reportFile) {
            String fileName = multipartFile.getOriginalFilename();
            System.out.println(fileName);
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/picture/";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            realPath = realPath + houseId + "\\";
            System.out.println(path);
            System.out.println(houseId);
            System.out.println(realPath);
            File targetFile = new File(realPath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            File dest = new File(realPath + fileName);
            try {
                multipartFile.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//            try {
////            MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
////            List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
////            MultipartFile file = null;
////            BufferedOutputStream stream = null;
//
//                // 1. 用数组MultipartFile[]来表示多文件,所以遍历数组,对其中的文件进行逐一操作
//                for (MultipartFile file : files) {
//                    // 2. 通过一顿file.getXXX()的操作,获取文件信息。
//                    // 2.1 这里用文件名举个栗子
//                    String filename = file.getOriginalFilename();
//                    // 3. 接下来调用方法来保存文件到本地磁盘,返回的是保存后的文件路径
//                    // 4. 保存文件信息到数据库
//                    // 4.1 搞个实体类，把你需要的文件信息保存到实体类中
//                    // 4.2 调用Service层或者Dao层，保存数据库即可。
//                }
//                String path = ResourceUtils.getURL("classpath:").getPath() + "static/picture/";
//                String realPath = path.replace('/', '\\').substring(1, path.length());
//                //用于查看路径是否正确
//                System.out.println(realPath);
//                //获取文件的名称
//                final String fileName = multipartFile.getOriginalFilename();
//                String filePath = realPath + fileName;
//                System.out.println(filePath);
//                File file = new File(filePath);
//                if (!file.exists()) {
//                    try {
//                        file.createNewFile();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                // 文件存储
//                try {
//                    multipartFile.transferTo(file);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//

        return Result.succful("");
    }

    @PostMapping("/uploadHouseExcle")
    public Result uploadHouseExcle(@RequestParam("file") MultipartFile multipartFile) throws FileNotFoundException {
//        System.out.println(reportFile);
        //文件上传的地址
//        for (MultipartFile multipartFile : multipartFiles) {
        System.out.println(multipartFile);
        String fileName = multipartFile.getOriginalFilename();
        System.out.println(fileName);
//        String path = ResourceUtils.getURL("classpath:").getPath() + "static/excle/";
//        String realPath = path.replace('/', '\\').substring(1, path.length());
        String path = ResourceUtils.getURL("classpath:").getPath() + "static/excle/";
        String realPath = path.replace('/', '\\').substring(1, path.length());
//        realPath = realPath + houseId + "\\";
        System.out.println(path);
//        System.out.println(houseId);
        System.out.println(realPath);
        File targetFile = new File(realPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        File dest = new File(realPath + fileName);
        try {
            multipartFile.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<TbHouseBase> tbHouseBases = readXLS.importXLS(multipartFile);
        for (TbHouseBase tbHouseBase : tbHouseBases) {
            System.out.println(tbHouseBase);
            System.out.println("--------");
        }

        boolean b = tbHouseBaseService.saveBatch(tbHouseBases);
        System.out.println(b);
//        }
        return Result.succful("上传成功");
    }


    @GetMapping("/deleteHouse")
    public Result deleteByhouseId(@RequestParam("houseId") Long houseId) {
        System.out.println("deleteByhouseId");
        System.out.println(houseId);
        UpdateWrapper<TbHouseBase> deleted = new UpdateWrapper<TbHouseBase>().set("deleted", 1).eq("house_id", houseId);
        boolean b = tbHouseBaseService.removeById(houseId);
        System.out.println(b);
        if (b) {
            return Result.succful("删除成功！");
        } else {
            return Result.fail("删除失败!");
        }
    }

    @PostMapping("/uploadExcle")
    public Result uploadCourseExcel(@RequestParam("files") MultipartFile multipartFile, HttpServletRequest request) {
        return Result.succful("");
    }

    @GetMapping("/updateHousePrice")
    public Result updateHousePrice(@RequestParam Long houseId, @RequestParam Float changePrice) {
        System.out.println(houseId);
        TbHouseBase house = tbHouseBaseService.getById(houseId);
        house.setHouseRentalPrice(changePrice);
        tbHouseBaseService.updateById(house);
        return Result.succful("成功");
    }

    @PostMapping("/updateHouseState")
    public Result updateHouseState(@RequestBody List<TbHouseBase> multipleSelection) {
//        System.out.println(houseId);
        System.out.println(multipleSelection);
        for (TbHouseBase houseBase : multipleSelection) {
            houseBase.setHouseState((short)1);
        }
        System.out.println(multipleSelection);
        tbHouseBaseService.saveOrUpdateBatch(multipleSelection);
        return Result.succful("成功");
    }


}
