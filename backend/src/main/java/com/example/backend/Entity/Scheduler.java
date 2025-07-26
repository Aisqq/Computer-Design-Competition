package com.example.backend.Entity;
import lombok.Data;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Data
public class Scheduler{
    PriorityQueue<Task> taskQueue = new PriorityQueue<>();
    Map<String,Integer> materialCounter;
    Map<String,Integer> runTimeMap = new HashMap<>();
    Integer isCarIdle;
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();                     //读写同步锁
    ReentrantLock taskLock = new ReentrantLock();                                   //任务同步锁
    private Map<String,Task> taskLabelMap = new HashMap<>();

    public Scheduler(Map<String, Integer> materialCounter, Integer isCarIdle){
        this.materialCounter = materialCounter;
        this.isCarIdle=isCarIdle;
        runTimeMap.put("A",10);
        runTimeMap.put("B",10);
        runTimeMap.put("C",10);
        runTimeMap.put("D",10);
        runTimeMap.put("E",10);
        runTimeMap.put("F",10);
        for (Map.Entry<String, Integer> entry : runTimeMap.entrySet()) {
            String taskName = entry.getKey();
            long currentTime = System.currentTimeMillis(); // 每次添加任务前获取当前时间
            taskQueue.add(new Task(taskName, taskName, currentTime, 0, entry.getValue()));
        }
        for(Task task : taskQueue){
            taskLabelMap.put(task.getLabel(),task);
        }
    }

    private Task findTaskByLabel(String label) {
        return taskLabelMap.get(label);
    }

    public String ScheduleTask() throws InterruptedException {
        Task nextTask =null;
        // 任务调度
        if(!taskQueue.isEmpty() && isCarIdle == 1) {
            //执行调度任务
            //1.首先更新优先级
            //2.取出优先级最高的任务
            taskLock.lock();
            try {
                updateTaskPriority();
                taskQueue.forEach(taskName -> System.out.println(taskName.getName() + ":" + taskName.getPriority() + ":" + taskName.getLabelCount() + ":" + taskName.getArrivalTime()));
                nextTask = taskQueue.peek();
            }finally {
                taskLock.unlock();
            }
        }
            return nextTask != null ? nextTask.getName() : null;
    }


    void updateTaskPriority() {
        lock.writeLock().lock();
        try {
            PriorityQueue<Task> updatedQueue = new PriorityQueue<>(taskQueue.size(), taskQueue.comparator());
            for (Task task : taskQueue) {
                String taskName = task.getLabel();
                if(materialCounter.containsKey(taskName)) {
                    int newLabelCount = materialCounter.get(taskName);
                    if(newLabelCount ==0){
                        task.setArrivalTime(System.currentTimeMillis());
                    }
                    task.setLabelCount(newLabelCount);
                    task.updatePriority(System.currentTimeMillis(), newLabelCount);
                }
                // 将任务添加到新队列
                updatedQueue.offer(task);
                taskQueue = updatedQueue;
            }

        }finally {
            lock.writeLock().unlock();
        }
    }

    public List<Taskinfo> getTaskPriorityList() {
        List<Taskinfo> taskInfoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lock.readLock().lock();
        try {
            for (Task task : taskQueue) {
                taskInfoList.add(new Taskinfo(task, sdf));
            }
        } finally {
            lock.readLock().unlock();
        }
        return taskInfoList;
    }
}
