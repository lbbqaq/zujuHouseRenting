package com.lbb.vuehousespringboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.lbb.vuehousespringboot.util.PictureUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.util.ResourceUtils;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

/**
 * <p>
 *
 * </p>
 *
 * @author lbb
 * @since 2021-01-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbHouseBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "house_id", type = IdType.AUTO)
    private Long houseId;

//    /**
//     * 标题
//     */
//    private String houseTitle;

    /**
     * 房主
     */
    private Long houseHostId;

    /**
     * 房屋出租面积
     */
    private Integer houseRentalArea;

    /**
     * 房屋楼层
     */
    private String houseFloor;

    /**
     * 房屋车位
     */
    private String houseParking;

    /**
     * 房屋朝向
     */
    private String houseOrientation;

    /**
     * 入住
     */
    private String moveIn;

    /**
     * 是否有电梯
     */
    private String houseElevator;

    /**
     * 房屋用水
     */
    private String houseWater;

    /**
     * 房屋用电
     */
    private String houseElectricity;

    /**
     * 房屋燃气
     */
    private String houseGas;

    /**
     * 房屋采暖
     */
    private String houseHeating;

    /**
     * 房屋租期
     */
    private String houseTenancy;

    /**
     * 看房预约
     */
//    @TableField("Showings")
    private String Showings;

    /**
     * 房租出租方式：整租/合租/不限
     */
    private String houseRentalMethod;

    /**
     * 房屋地址：城市/行政区/小区/地址
     */
    private String houseAddress;

    /**
     * 房屋经度
     */
    private String houseLongitude;

    /**
     * 房屋纬度
     */
    private String houseLatitude;

    /**
     * 房屋布局
     */
    private String houseLayout;

    /**
     * 房屋出租价格
     */
    private Float houseRentalPrice;

    /**
     * 房源维护时间
     */
    private LocalDateTime houseUpdateTime;


    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 房屋备注
     */
    private String houseNote;

    /**
     * 房屋付款方式：季付/月付
     */
    private String housePaymentMethod;

//    /**
//     * 配套设施， 如：1,2,3
//     */
//    private String houseFacilities;

    /**
     * 出租状态
     */
    private Short houseState;

    /**
     * 门牌号
     */
    private String houseNumber;

    /**
     * 城市
     */
    private String houseCity;
    /**
     * 区县
     */
    private String houseDistrict;

    /**
     * 小区
     */
    private String houseVillage;
    /**
     * 房屋图片地址
     */
    private String housePicName;



    //查询的时候不显示该字段
    @TableField(select = false)
    @TableLogic
    private Integer deleted;

    /**
     * 房屋图片全路径地址
     */
    @TableField(exist = false)
    private List<String> housePicRealAddress;

    public List<String> getHousePicRealAddress()  {
        if(this.housePicName!=null){
            String fileName = this.housePicName;
            List<String> fileNames = PictureUtil.readPicList(housePicName);
            return fileNames;
        }else{
            return housePicRealAddress;
        }
    }

    public void setHousePicRealAddress(List<String> housePicRealAddress) {
        if(this.housePicName!=null){
            String fileName = this.housePicName;
            List<String> fileNames = PictureUtil.PicList(fileName);
            this.housePicRealAddress = fileNames;
        }else{
            this.housePicRealAddress =housePicRealAddress;
        }


    }
//
//    public List<String> getHousePicRealAddress() throws FileNotFoundException {
//        String fileName=this.housePicName;
//        List<String> fileNames= PictureUtil.PicList(housePicName);
//        this.housePicRealAddress=fileNames;
//        return housePicRealAddress;
//    }

    /**
     * 标签
     */
    private String houseTag;

    @TableField(exist = false)
    private List<TbTag> tags;

    @TableField(exist = false)
    private TbUser user;


}
