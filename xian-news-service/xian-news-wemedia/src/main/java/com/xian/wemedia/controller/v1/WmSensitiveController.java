package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.SensitiveDto;
import com.xian.model.wemedia.pojos.WmSensitive;
import com.xian.wemedia.service.WmSensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-24
 */
@RestController
@RequestMapping("/api/v1/sensitive")
public class WmSensitiveController {

    @Autowired
    WmSensitiveService wmSensitiveService;

    @DeleteMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") Integer id){

        return wmSensitiveService.deleteById(id);
    }

    /**
     * 展示敏感词
     * @return
     */
    @PostMapping("/list")
    public ResponseResult listPage(@RequestBody SensitiveDto dto){
        return wmSensitiveService.listPage(dto);
    }

    /**
     * 保存
     * @param sensitive
     * @return
     */
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody WmSensitive sensitive){
        return wmSensitiveService.insert(sensitive);

    }

    /**
     * 修改
     * @param sensitive
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(@RequestBody WmSensitive sensitive){
        return wmSensitiveService.update(sensitive);
    }
}
