package com.example.backend.Controller.dto;

import lombok.Data;

//封装返回数据
@Data
public class Logindto {
    private Integer userId;
    private String userRole;
    private String userName;
}
