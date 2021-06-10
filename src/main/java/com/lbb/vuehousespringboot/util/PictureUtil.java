package com.lbb.vuehousespringboot.util;

import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PictureUtil {

    public static List<String> PicList(String picName)  {
        List<String> picPaths=new ArrayList<>();
        try{
            List<String> list= new ArrayList<>();
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/picture/";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            list= Stream.of(picName.split(",")).map(String::toString).collect(Collectors.toList());
            for(String name:list){
                String picPath=realPath+name;
                picPaths.add(picPath);
            }
            System.out.println(picPaths);
        }catch(Exception e){
            e.printStackTrace();
        }
        return picPaths;
    }

    public static List<String> readPicList(String picName)  {
        List<String> picPaths=new ArrayList<>();
        try{
            List<String> list= new ArrayList<>();
            list= Stream.of(picName.split(",")).map(String::toString).collect(Collectors.toList());
            for(String name:list){
                picPaths.add(name);
            }
            System.out.println(picPaths);
        }catch(Exception e){
            e.printStackTrace();
        }
        return picPaths;
    }
//
//    public static void main(String[] args) {
//        System.out.println(  PicList("123,321,csad"));
//    }

    public static String UserAvar(String picName)  {
        String picPaths=new String();
        try{
            String path = ResourceUtils.getURL("classpath:").getPath() + "static/user/";
            String realPath = path.replace('/', '\\').substring(1, path.length());
            picPaths=realPath+picName;
            System.out.println(picPaths);
        }catch(Exception e){
            e.printStackTrace();
        }
        return picPaths;
    }

}
