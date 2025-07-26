package com.example.backend.Entity;

//这个包专门用来存放与数据库相关的class文件，与数据表一一对应。

import lombok.Data;

import java.sql.Date;

//这里get/set方法可以通过Lombok.data注解使用，如果要用去下载插件

@Data           //简化了get/set方法
public class warning {
    private Integer warningid;
    private Date warningtime;
    private String warningtype;
    private String warninglevel;
    private Integer labelid;
}
