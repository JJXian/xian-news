package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-09
 */
@RestController
@RequestMapping("/api/v1/channel")
public class WmChannelController {

    @Autowired
    WmChannelService wmChannelService;

    @GetMapping("/channels")
    public ResponseResult findAll(){
        return wmChannelService.findAll();
    }
}
