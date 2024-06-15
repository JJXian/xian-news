package com.xian.schedule.service.impl;

import com.xian.model.schedule.dtos.Task;
import com.xian.schedule.ScheduleApplication;
import com.xian.schedule.service.TaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ScheduleApplication.class)
@RunWith(SpringRunner.class)
class TaskServiceImplTest {

    @Autowired
    private TaskService taskService;

    @Test
    void addTask() {
        Task task = new Task();
        task.setTaskType(100);
        task.setPriority(50);
        task.setParameters("tasl test".getBytes());
        task.setExecuteTime(new Date().getTime()+1000);
        long taskId = taskService.addTask(task);
        System.out.println(taskId);

    }

    @Test
    void cancelTask(){
        taskService.cancelTask(1801888762351857666L);
    }
}