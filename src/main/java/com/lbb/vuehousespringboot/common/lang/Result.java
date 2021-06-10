package com.lbb.vuehousespringboot.common.lang;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;


    public static Result succful(int code,String msg,Object data){
        Result result=new Result();
        result.code=code;
        result.msg=msg;
        result.data=data;
        return  result;
    }
    public static Result succful(String msg,Object data){
        return  succful(200,msg,data);
    }
    public static Result succful(Object data){
        return  succful(200,"操作成功",data);
    }

    public static Result fail(int code,String msg,Object data){
        Result r=new Result();
        r.code=code;
        r.data=data;
        r.msg=msg;
        return r;
    }
    public static Result fail(String msg){
        return fail(400,msg,null);
    }
    public static Result fail(String msg,Object data){
        return fail(400,msg,data);
    }
}
