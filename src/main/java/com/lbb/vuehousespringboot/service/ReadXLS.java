package com.lbb.vuehousespringboot.service;
import com.lbb.vuehousespringboot.entity.TbHouseBase;
import com.lbb.vuehousespringboot.entity.TbUser;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadXLS {
    public List<TbHouseBase> importXLS(MultipartFile multipartFile) {
        ArrayList<TbHouseBase> list = new ArrayList<>();
        try {
            String fileName = "租房.xls";
            /*输入流*/
            InputStream inputStream = new FileInputStream("E:\\github\\vue-house-springboot\\target\\classes\\static\\excle\\" +fileName);
            /*提供读写Microsoft Excel格式档案的功能。创建HSSFWorkBook对象*/
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            /*获取HSSFWorkBook对象第一张excel表*/
            HSSFSheet sheetAt = workbook.getSheetAt(0);
            /*遍历每行*/

            for (Row row : sheetAt) {
                /*判断行数是否大于0.第一行表头，不读取第一行的表头内容，也即rowNum=0的行*/
                if (row.getRowNum() == 0) {
                    continue;
                }
                TbHouseBase tbHouseBase=new TbHouseBase();
//                if(row.getCell(0).getStringCellValue()!=null){
//                    row.getCell(0).setCellType(CellType.STRING);
//                    tbHouseBase.setHouseId(Long.parseLong(row.getCell(0).getStringCellValue()));
//                }
                tbHouseBase.setHouseAddress((row.getCell(1).getStringCellValue()));
                tbHouseBase.setHouseNumber((row.getCell(2).getStringCellValue()));
                tbHouseBase.setHouseLayout((row.getCell(3).getStringCellValue()));
                tbHouseBase.setHouseDistrict((row.getCell(4).getStringCellValue()));
                tbHouseBase.setHouseRentalPrice((float) row.getCell(5).getNumericCellValue());
                tbHouseBase.setHouseCity((row.getCell(6).getStringCellValue()));
                tbHouseBase.setHouseVillage((row.getCell(7).getStringCellValue()));
                tbHouseBase.setHouseHostId((long) row.getCell(8).getNumericCellValue());
                tbHouseBase.setHouseRentalArea((int) row.getCell(9).getNumericCellValue());
                tbHouseBase.setHouseFloor((row.getCell(10).getStringCellValue()));
                tbHouseBase.setHouseParking((row.getCell(11).getStringCellValue()));
                tbHouseBase.setHouseOrientation((row.getCell(12).getStringCellValue()));
                tbHouseBase.setMoveIn((row.getCell(13).getStringCellValue()));
                tbHouseBase.setHouseElevator((row.getCell(14).getStringCellValue()));
                tbHouseBase.setHouseWater((row.getCell(15).getStringCellValue()));
                tbHouseBase.setHouseElectricity((row.getCell(16).getStringCellValue()));
                tbHouseBase.setHouseGas((row.getCell(17).getStringCellValue()));
                tbHouseBase.setHouseHeating((row.getCell(18).getStringCellValue()));
                tbHouseBase.setHouseTenancy((row.getCell(19).getStringCellValue()));
                tbHouseBase.setHouseRentalMethod((row.getCell(20).getStringCellValue()));
                row.getCell(21).setCellType(CellType.STRING);
                row.getCell(22).setCellType(CellType.STRING);
                tbHouseBase.setHouseLongitude((row.getCell(21).getStringCellValue()));
                tbHouseBase.setHouseLatitude((row.getCell(22).getStringCellValue()));
                tbHouseBase.setCreateTime(LocalDateTime.now());
                tbHouseBase.setHouseUpdateTime(LocalDateTime.now());
                tbHouseBase.setHouseNote((row.getCell(25).getStringCellValue()));
                tbHouseBase.setHousePaymentMethod((row.getCell(26).getStringCellValue()));
                tbHouseBase.setHouseState((short) row.getCell(27).getNumericCellValue());
                tbHouseBase.setHousePicName((row.getCell(28).getStringCellValue()));
                tbHouseBase.setHouseTag((row.getCell(29).getStringCellValue()));
                tbHouseBase.setHouseState((short) 0);
//                user.setUserId(userId);
//                user.setVia(via);
//                user.setUserName(userName);
//                user.setUserPassword(userPassword);
//                user.setPhone(phone);
//                user.setAddress(address);
//                user.setUserEmail(userEmail);
//                user.setBalance(balance);
//                user.setPayPwd(payPwd);
//                list.add(user);
                list.add(tbHouseBase);
            }
            /*关闭流*/
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}