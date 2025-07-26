package com.example.backend.Entity;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


@Data
public class Task implements Comparable<Task>{
    String name;
    String label;
    long arrivalTime;
    int labelCount;
    double priority;
    long runTime;


    Task(String name, String label, long arrivalTime, int labelCount, long runTime) {
        this.name = name;
        this.label = label;
        this.arrivalTime = arrivalTime;
        this.labelCount = labelCount;
        this.runTime = runTime;
        updatePriority(arrivalTime,labelCount); // 使用当前时间初始化优先级
    }

    void updatePriority(long currentTime , int labelCount) {
       long waitTime =currentTime - arrivalTime;
        priority = ((double) waitTime * labelCount) / (runTime*60);
    }

    @Override
    public int compareTo(Task otherTask) {
        int priorityComparison = Double.compare(otherTask.priority, this.priority);
        if (priorityComparison != 0) {
            return priorityComparison;
        }
        // 如果优先级相同，比较到达时间
        return Long.compare(this.arrivalTime, otherTask.arrivalTime);
    }
}
