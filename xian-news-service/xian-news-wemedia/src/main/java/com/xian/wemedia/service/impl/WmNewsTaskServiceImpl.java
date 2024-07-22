package com.xian.wemedia.service.impl;

import com.alibaba.fastjson.JSON;
import com.xian.apis.schedule.IScheduleClient;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.TaskTypeEnum;
import com.xian.model.schedule.dtos.Task;
import com.xian.model.wemedia.pojos.WmNews;
import com.xian.utils.common.ProtostuffUtil;
import com.xian.wemedia.service.WmNewsAutoScanService;
import com.xian.wemedia.service.WmNewsTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@Slf4j
public class WmNewsTaskServiceImpl implements WmNewsTaskService {

//    延迟任务远程接口
    @Autowired
    private IScheduleClient scheduleClient;

    /**
     * 添加任务到延迟队列中
     * @param id          文章的id
     * @param publishTime 发布的时间  可以做为任务的执行时间
     */
    @Override
    @Async
    public void addNewsToTask(Integer id, Date publishTime) {

        log.info("添加任务到延迟服务中----begin");

        Task task = new Task();
        task.setExecuteTime(publishTime.getTime());
        task.setTaskType(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType());
        task.setPriority(TaskTypeEnum.NEWS_SCAN_TIME.getPriority());
        WmNews wmNews = new WmNews();
        wmNews.setId(id);
        task.setParameters(ProtostuffUtil.serialize(wmNews));

        scheduleClient.addTask(task);

        log.info("添加任务到延迟服务中----end");

    }

    @Autowired
    private WmNewsAutoScanService wmNewsAutoScanService;

    /**
     * 消费任务，审核文章
     */
    @Scheduled(fixedRate = 10000)
    @Override
    public void scanNewsByTask() {

        log.info("文章审核---消费任务执行---begin---");

        ResponseResult responseResult = scheduleClient.poll(TaskTypeEnum.NEWS_SCAN_TIME.getTaskType(), TaskTypeEnum.NEWS_SCAN_TIME.getPriority());

        if(responseResult.getCode().equals(200) && responseResult.getData() != null){
//            Task task = JSON.parseObject(JSON.toJSONString(responseResult.getData()), Task.class);
            String json_str = JSON.toJSONString(responseResult.getData());
            Task task = JSON.parseObject(json_str, Task.class);
            WmNews wmNews = ProtostuffUtil.deserialize(task.getParameters(), WmNews.class);
            wmNewsAutoScanService.autoScanWmNews(wmNews.getId());

        }
        log.info("文章审核---消费任务执行---end---");
    }
}
