package com.example.backend.Service.Impl;

import com.example.backend.Entity.Scheduler;
import com.example.backend.Entity.Taskinfo;
import com.example.backend.Service.CarService;
import com.example.backend.Service.MaterialService;
import com.example.backend.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class SchedulerServiceImpl implements ScheduleService {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private CarService carService;
    private Scheduler scheduler;

    @PostConstruct
    public void init() {
        try {
            Map<String, Integer> materialCounter = materialService.addlabel();
            Integer isCarIdle = carService.SelectcarStatus() != null && !carService.SelectcarStatus().isEmpty() ? carService.SelectcarStatus().get(0) : null;
            this.scheduler = new Scheduler(materialCounter, isCarIdle);
        } catch (Exception e) {
            // 处理异常，例如记录日志
            e.printStackTrace();
        }
    }

    public void refreshScheduler() {
        // 获取最新的 materialCounter 和 isCarIdle
        Map<String, Integer> newMaterialCounter = materialService.addlabel();
        Integer newIsCarIdle = carService.SelectcarStatus().get(0);

        // 更新 Scheduler 的状态
        scheduler.setMaterialCounter(newMaterialCounter);
        scheduler.setIsCarIdle(newIsCarIdle);
    }

    public String ScheduleTask() {
        // 在调度任务前更新状态
        refreshScheduler();
        try {
            String taskname = scheduler.ScheduleTask();
            if (taskname != null) {
                System.out.println("调度的任务是: " + taskname);
//                MessageSender.sendMessage(taskname);
                return taskname;
            } else {
                System.out.println("没有任务可调度");
                return "notTask";
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Taskinfo> getCurrentTaskPriorityList() {
        return scheduler.getTaskPriorityList();
    }
}
