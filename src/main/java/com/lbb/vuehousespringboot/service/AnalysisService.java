package com.lbb.vuehousespringboot.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lbb.vuehousespringboot.common.vo.HouseCItyVO;
import com.lbb.vuehousespringboot.common.vo.analysis.CityAnalysis;
import com.lbb.vuehousespringboot.common.vo.analysis.OrderAnalysis;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbOrder;
import com.lbb.vuehousespringboot.mapper.TbOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class AnalysisService {

    @Autowired
    private TbOrderMapper tbOrderMapper;
    @Autowired
    private TbHouseBaseService tbHouseBaseService;


    public OrderAnalysis getOrderAnalysisCity() {
        System.out.println("/AnalysisService/getOrderAnalysis");
        OrderAnalysis orderAnalysis = new OrderAnalysis();
//获得所有的城市
        List<HouseCItyVO> allHouseCity = tbHouseBaseService.findAllHouseCity();
        HashMap<String, Integer> hashMapCity = new HashMap();
        HashMap<String, Float> hashMapPrice = new HashMap();
//初始化所有的hashMap
        for (HouseCItyVO houseCItyVO : allHouseCity) {
            hashMapCity.put(houseCItyVO.getCity(), 0);
            hashMapPrice.put(houseCItyVO.getCity(), 0f);
        }

        List<TbOrder> list = tbOrderMapper.findAllOrders(new QueryWrapper<TbOrder>().eq("1", 1));

        for (TbOrder order : list) {
            String houseCity = order.getHouses().getHouseCity();

//            将订单数量放进houseMapCity
            hashMapCity.put(houseCity, (int) hashMapCity.get(houseCity) + 1);
            hashMapCity.put("不限",(int) hashMapCity.get("不限") + 1);

//            叠加订单总价格
            hashMapPrice.put(houseCity, hashMapPrice.get(houseCity)+order.getTotalPrice());
            hashMapPrice.put("不限", hashMapPrice.get("不限")+order.getTotalPrice());

            order.getTotalPrice();
        }

        System.out.println(hashMapCity);
        System.out.println(hashMapPrice);
//        System.out.println(hashMapCity);
//        获得hashMap
        List citys=new ArrayList();
        List number=new ArrayList();
        List<Float> prices=new ArrayList<Float>();
        for(String key:hashMapCity.keySet()){
            citys.add(key);
            number.add(hashMapCity.get(key));
            if(hashMapCity.get(key)!=0){
                prices.add((hashMapPrice.get(key)/hashMapCity.get(key)));
            }

        }



        orderAnalysis.setCitys(citys);
        orderAnalysis.setCityNumber(number);
        orderAnalysis.setCityPrices(prices);

        
        System.out.println(orderAnalysis);
        return orderAnalysis;
    }


    public CityAnalysis getCityDetailAnalysis(String cityName) {
        System.out.println("/AnalysisService/getCityDetailAnalysis");
        System.out.println(cityName);
        List<TbOrder> list = tbOrderMapper.findAllOrders(new QueryWrapper<TbOrder>().eq("1", 1).orderByAsc("create_time"));
        List<String> list1=new ArrayList<>();
        List<Float> list2=new ArrayList<>();
        LinkedHashMap<String,Float> map =new LinkedHashMap<String,Float>();
        System.out.println(list);
        for (TbOrder order : list) {
            if (order.getHouses().getHouseCity().equals(cityName)) {
                System.out.println(order.getCreateTime());
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String format = dtf2.format(order.getCreateTime());
                map.put(format,map.getOrDefault(format,0f)+order.getTotalPrice());
//                list2.add(order.getTotalPrice());
            }
            if(cityName.equals("不限")){
                DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String format = dtf2.format(order.getCreateTime());
                map.put(format,map.getOrDefault(format,0f)+order.getTotalPrice());
//                list1.add(format);
//                list2.add(order.getTotalPrice());
            }
        }
        for(String key:map.keySet()){
            list1.add(key);
            list2.add(map.get(key));
        }
        System.out.println(map);
//        System.out.println(list1);
        CityAnalysis cityAnalysis=new CityAnalysis();
        cityAnalysis.setPrices(list2);
        cityAnalysis.setCreateTimes(list1);
        return cityAnalysis;
    }


}
