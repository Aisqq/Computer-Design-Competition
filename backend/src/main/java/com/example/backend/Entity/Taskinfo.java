package com.example.backend.Entity;


import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Taskinfo {
    private String name;
    private String formattedArrivalTime; // 格式化的到达时间
    private int labelCount;
    private double priority;
    private long runTime;

    public Taskinfo(Task task, SimpleDateFormat sdf) {
        this.name = task.getName();
        this.formattedArrivalTime = sdf.format(new Date(task.getArrivalTime()));
        this.labelCount = task.getLabelCount();
        this.priority = task.getPriority();
        this.runTime = task.getRunTime();
    }
}
