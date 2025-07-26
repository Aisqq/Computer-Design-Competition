package com.example.backend.common;

//包装后台返回给前端的数据,使得返回结果统一

import lombok.Data;

@Data
public class Result {
    private static final  String SUCCESS_CODE = "success";
    private static final  String ERROR_CODE = "-1";     //判断返回的结果成功还是失败
    private String code;
    private String msg;
    private Object data;

    public  static  Result success(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;      //无参数，成功不返回数据，例如插入成功不需要返回数据
    }
    public  static  Result success(Object data){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;      //成功返回数据，例如查询成功需要返回数据
    }

    public  static  Result error(String msg){       //错误的话，返回错误信息
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }
}
