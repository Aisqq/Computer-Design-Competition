package com.example.backend.Entity;

import lombok.Data;

@Data
public class Machine {
    private Integer machineId;
    private String machineName;
    private Integer labelId;
    private String machineType;
    private String machineStatus;
    private Integer materialId;

}
