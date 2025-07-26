package com.example.backend.Service;

import com.example.backend.Entity.Taskinfo;

import java.util.List;
import java.util.Map;
import java.util.Queue;

public interface ScheduleService {

    String ScheduleTask ();

    List<Taskinfo> getCurrentTaskPriorityList();
}
