package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.ChannelDto;
import com.xian.model.wemedia.pojos.WmChannel;
import com.xian.wemedia.service.WmChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/list")
    public ResponseResult findByNameAndPage(@RequestBody ChannelDto dto){
        return wmChannelService.findByNameAndPage(dto);
    }

    @PostMapping("/save")
    public ResponseResult insert(@RequestBody WmChannel adChannel){
        return wmChannelService.insert(adChannel);
    }

    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmChannel adChannel){
        return wmChannelService.update(adChannel);
    }

    /**
     * delete by id
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id){
        return wmChannelService.deleteById(id);
    }
}
