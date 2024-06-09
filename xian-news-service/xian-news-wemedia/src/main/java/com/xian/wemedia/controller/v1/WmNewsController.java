package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.WmNewsDto;
import com.xian.model.wemedia.dtos.WmNewsPageReqDto;
import com.xian.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-09
 */
@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return  wmNewsService.findAll(dto);
    }

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return  wmNewsService.submitNews(dto);
    }


}
