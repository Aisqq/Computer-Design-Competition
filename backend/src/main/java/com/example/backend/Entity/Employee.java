package com.example.backend.Entity;

import cn.hutool.core.annotation.Alias;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;

@Data
public class Employee {

    @Alias("工号")
    private Integer employeeId;
    @Alias("标签号")
    private Integer labelId;
    @Alias("姓名")
    private String employeeName;
    @Alias("岗位")
    private String employeePosition;
    @Alias("工作状态")
    private String employeeStatus;
    @Alias("电话号码")
    private String employeePhone;
    @Alias("上岗日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 格式应与数据库中的日期格式一致
    private Date employeeWorkdate;

}
