package com.xian.schedule.service;

import com.xian.model.schedule.dtos.Task;

public interface TaskService {

    /**
     * 添加任务
     * @param task   任务对象
     * @return       任务id
     */
    public long addTask(Task task) ;

    /**
     * 取消任务
     * @param taskId        任务id
     * @return              取消结果
     */
    public boolean cancelTask(long taskId);

    /**
     * 消费任务
     * 按照类型和优先级来拉取任务
     * @param type
     * @param priority
     * @return
     */
    public Task poll(int type,int priority);

    /**
     * 实现未来的定时刷新
     */
    public void refresh();

}