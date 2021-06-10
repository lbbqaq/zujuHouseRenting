package com.lbb.vuehousespringboot.service;

import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbUser;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WriteXLS {
    public void exportExcelUser(List<TbUser> list) {
        /*创建HSSFWorkBook对象*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        /*创建excel表*/
        HSSFSheet sheet = hssfWorkbook.createSheet();
        /*在当前表中创建行，第一行也就是rowNum为0的行一般为表头行*/
        HSSFRow titleRow = sheet.createRow(0);
        /*给当前行titleRow创建单元格，并使用setCellValue方法赋值。根据实际需求创建。与数据库对应*/
        titleRow.createCell(0).setCellValue("用户编号");
        titleRow.createCell(1).setCellValue("用户名");
        titleRow.createCell(2).setCellValue("真实名字");
        titleRow.createCell(3).setCellValue("头像");
        titleRow.createCell(4).setCellValue("密码");
//        titleRow.createCell(5).setCellValue("创建时间");
//        titleRow.createCell(6).setCellValue("最后登录时间");
        titleRow.createCell(5).setCellValue("手机号");
        titleRow.createCell(6).setCellValue("身份证");
        titleRow.createCell(7).setCellValue("性别");
        for (TbUser user : list) {
            //获取当前最大的行
            int lastRowNum = sheet.getLastRowNum();
            //新创建一行操作
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(user.getId());
            dataRow.createCell(1).setCellValue(user.getUsername() == null ? "" : user.getUsername());
            dataRow.createCell(2).setCellValue(user.getRealName() == null ? "" : user.getRealName());
            dataRow.createCell(3).setCellValue(user.getAvatar() == null ? "" : user.getAvatar());
            dataRow.createCell(4).setCellValue(user.getPassword() == null ? "" : user.getPassword());
//            dataRow.createCell(5).setCellValue(user.getCreated() == null ? "" : user.getCreated());
            dataRow.createCell(5).setCellValue(user.getPhone() == null ? 18420010795L : user.getPhone());
            dataRow.createCell(6).setCellValue(user.getIdCard() == null ? 1L : user.getIdCard());
            dataRow.createCell(7).setCellValue(user.getSex() == null ? "" : user.getSex());
        }
        /*创建输出流及本地存储地址*/
        String fileName = "用户.xls";
        try {
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/excle/";
            String realPath = path.replace('/', '\\').substring(1, path.length());
//            FileOutputStream fileOut = new FileOutputStream(realPath+fileName);
            FileOutputStream fileOut = new FileOutputStream("E:\\github\\vue-house-springboot\\src\\main\\resources\\static\\excle" +fileName);
            /*写出到本地*/
            hssfWorkbook.write(fileOut);
            /*关闭流*/
            hssfWorkbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void exportExcelHouse(List<TbHouseBase> list) {
        /*创建HSSFWorkBook对象*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        /*创建excel表*/
        HSSFSheet sheet = hssfWorkbook.createSheet();
        /*在当前表中创建行，第一行也就是rowNum为0的行一般为表头行*/
        HSSFRow titleRow = sheet.createRow(0);
        /*给当前行titleRow创建单元格，并使用setCellValue方法赋值。根据实际需求创建。与数据库对应*/
        titleRow.createCell(0).setCellValue("房屋id");
        titleRow.createCell(1).setCellValue("房屋地址");
        titleRow.createCell(2).setCellValue("门牌号");
        titleRow.createCell(3).setCellValue("布局");
        titleRow.createCell(4).setCellValue("标题");
        titleRow.createCell(5).setCellValue("租金");
        titleRow.createCell(6).setCellValue("城市");
        titleRow.createCell(7).setCellValue("小区");
        titleRow.createCell(8).setCellValue("房主id");
        titleRow.createCell(9).setCellValue("面积");
        titleRow.createCell(10).setCellValue("楼层");
        titleRow.createCell(11).setCellValue("停车位");
        titleRow.createCell(12).setCellValue("朝向");
        titleRow.createCell(13).setCellValue("是否直接搬进");
        titleRow.createCell(14).setCellValue("电梯");
        titleRow.createCell(15).setCellValue("用水");
        titleRow.createCell(16).setCellValue("用电");
        titleRow.createCell(17).setCellValue("燃气");
        titleRow.createCell(18).setCellValue("采暖");
        titleRow.createCell(19).setCellValue("租期");
        titleRow.createCell(20).setCellValue("租赁方式");
        titleRow.createCell(21).setCellValue("经度");
        titleRow.createCell(22).setCellValue("纬度");
        titleRow.createCell(23).setCellValue("更新时间");
        titleRow.createCell(24).setCellValue("创建时间");
        titleRow.createCell(25).setCellValue("注意事项");
        titleRow.createCell(26).setCellValue("支付方式");
        titleRow.createCell(27).setCellValue("状态");
        titleRow.createCell(28).setCellValue("图片名字");
        titleRow.createCell(29).setCellValue("标签名字");
        for (TbHouseBase houseBase : list) {
            //获取当前最大的行
            int lastRowNum = sheet.getLastRowNum();
            //新创建一行操作
            HSSFRow dataRow = sheet.createRow(lastRowNum + 1);
            dataRow.createCell(0).setCellValue(houseBase.getHouseId());
            dataRow.createCell(1).setCellValue(houseBase.getHouseAddress() == null ? "" : houseBase.getHouseAddress());
            dataRow.createCell(2).setCellValue(houseBase.getHouseNumber() == null ? "" : houseBase.getHouseNumber());
            dataRow.createCell(3).setCellValue(houseBase.getHouseLayout() == null ? "" : houseBase.getHouseLayout());
//            dataRow.createCell(5).setCellValue(user.getCreated() == null ? "" : user.getCreated());
            dataRow.createCell(4).setCellValue(houseBase.getHouseVillage() == null ? "" : houseBase.getHouseVillage());
            dataRow.createCell(5).setCellValue(houseBase.getHouseRentalPrice() == null ? 1L : houseBase.getHouseRentalPrice());
            dataRow.createCell(6).setCellValue(houseBase.getHouseCity() == null ? "" : houseBase.getHouseCity());
            dataRow.createCell(7).setCellValue(houseBase.getHouseVillage() == null ? "" : houseBase.getHouseVillage());
            dataRow.createCell(8).setCellValue(houseBase.getHouseHostId() == null ? null : houseBase.getHouseHostId());
            dataRow.createCell(9).setCellValue(houseBase.getHouseRentalArea() == null ? 0 : houseBase.getHouseRentalArea());
            dataRow.createCell(10).setCellValue(houseBase.getHouseFloor() == null ? "" : houseBase.getHouseFloor());
            dataRow.createCell(11).setCellValue(houseBase.getHouseParking() == null ? "" : houseBase.getHouseParking());
            dataRow.createCell(12).setCellValue(houseBase.getHouseOrientation() == null ? "" : houseBase.getHouseOrientation());
            dataRow.createCell(13).setCellValue(houseBase.getMoveIn() == null ? "" : houseBase.getMoveIn());
            dataRow.createCell(14).setCellValue(houseBase.getHouseElevator() == null ? "" : houseBase.getHouseElevator());
            dataRow.createCell(15).setCellValue(houseBase.getHouseWater() == null ? "" : houseBase.getHouseWater());
            dataRow.createCell(16).setCellValue(houseBase.getHouseElectricity() == null ? "" : houseBase.getHouseElectricity());
            dataRow.createCell(17).setCellValue(houseBase.getHouseGas() == null ? "" : houseBase.getHouseGas());
            dataRow.createCell(18).setCellValue(houseBase.getHouseHeating() == null ? "" : houseBase.getHouseHeating());
            dataRow.createCell(19).setCellValue(houseBase.getHouseTenancy() == null ? "" : houseBase.getHouseTenancy());
            dataRow.createCell(20).setCellValue(houseBase.getHouseRentalMethod() == null ? "" : houseBase.getHouseRentalMethod());
            dataRow.createCell(21).setCellValue(houseBase.getHouseLongitude() == null ? "" : houseBase.getHouseLongitude());
            dataRow.createCell(22).setCellValue(houseBase.getHouseLatitude() == null ? "" : houseBase.getHouseLatitude());
            dataRow.createCell(23).setCellValue(houseBase.getHouseUpdateTime().toString() == null ? "" : houseBase.getHouseUpdateTime().toString());
            dataRow.createCell(24).setCellValue(houseBase.getCreateTime().toString() == null ? "" : houseBase.getCreateTime().toString());
            dataRow.createCell(25).setCellValue(houseBase.getHouseNote() == null ? "" : houseBase.getHouseNote());
            dataRow.createCell(26).setCellValue(houseBase.getHousePaymentMethod() == null ? "" : houseBase.getHousePaymentMethod());
            dataRow.createCell(27).setCellValue(houseBase.getHouseState() == null ? 1 : houseBase.getHouseState());
            dataRow.createCell(28).setCellValue(houseBase.getHousePicName() == null ? "" : houseBase.getHousePicName());
            dataRow.createCell(29).setCellValue(houseBase.getHouseTag() == null ? "" : houseBase.getHouseTag());
        }
        /*创建输出流及本地存储地址*/
        String fileName = "租房.xls";
        try {
//            String path = ResourceUtils.getURL("classpath:").getPath() + "static/excle/";
//            String realPath = path.replace('/', '\\').substring(1, path.length());
//            FileOutputStream fileOut = new FileOutputStream(realPath+fileName);
            FileOutputStream fileOut = new FileOutputStream("E:\\github\\vue-house-springboot\\src\\main\\resources\\static\\excle\\" +fileName);
            /*写出到本地*/
            hssfWorkbook.write(fileOut);
            /*关闭流*/
            hssfWorkbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
