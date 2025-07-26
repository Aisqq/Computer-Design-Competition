package com.example.backend.Controller.request;

import lombok.Data;

//模糊查询，按照名字或工号查询
@Data
public class warningPageRequest extends BaseRequest {
    private Integer warningid;
    private String warningtype;
    private String warninglevel;
}
