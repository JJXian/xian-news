package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.WmMaterialDto;
import com.xian.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-08
 */
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController {

    @Autowired
    private WmMaterialService wmMaterialService;

    /**
     * 素材上传
     * @param multipartFile
     * @return
     */
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile){
        return wmMaterialService.uploadPicture(multipartFile);
    }

    /**
     * 素材展示
     * @param dto
     * @return
     */
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto){
        ResponseResult responseResult =   wmMaterialService.findList(dto);
        return responseResult;
    }
}
