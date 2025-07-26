package com.example.backend.Entity;

import lombok.Data;

@Data
public class Car {
    private Integer carId;
    private Integer labelId;
    private String carStatus;
    private String carRemark;
    private Integer employeeId;
    private String geom_x;
    private String geom_y;
    private String geom_z;
}
