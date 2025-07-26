package com.example.backend.Controller;

import com.example.backend.Entity.Taskinfo;
import com.example.backend.Service.ScheduleService;
import com.example.backend.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    @Autowired
    ScheduleService schedulerService;

    @GetMapping("/task")
    public Result ScheduleTask(){
        return Result.success(schedulerService.ScheduleTask());
    }

    @GetMapping("/task-priorities")
    public Result getTaskPriorities() {
              // 调用服务层方法获取任务优先级列表
            List<Taskinfo> taskPriorities = schedulerService.getCurrentTaskPriorityList();
            // 如果获取成功，返回包含任务优先级列表的Result对象
            return Result.success(taskPriorities);
    }
}
