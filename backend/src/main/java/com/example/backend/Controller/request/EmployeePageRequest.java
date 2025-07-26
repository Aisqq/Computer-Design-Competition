package com.example.backend.Controller.request;

import lombok.Data;

//模糊查询，按照名字或工号查询
@Data
public class EmployeePageRequest extends BaseRequest {
    private String employeeName;

}
