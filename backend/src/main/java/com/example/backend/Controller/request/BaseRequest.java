package com.example.backend.Controller.request;

//分页查询的基类，每个分页查询都会用到他

import lombok.Data;

@Data
public class BaseRequest {
    private int pageNum =1;
    private int pageSize =10;//给一个默认值
}
