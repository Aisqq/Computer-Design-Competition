package com.example.backend.Entity;

//这个包专门用来存放与数据库相关的class文件，与数据表一一对应。

import lombok.Data;

//这里get/set方法可以通过Lombok.data注解使用，如果要用去下载插件

@Data           //简化了get/set方法
public class User {
    private Integer userId;
    private String userPwd;
    private String userRole;
    private String userName;


}
