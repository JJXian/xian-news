package com.xian.wemedia.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.wemedia.dtos.WmMaterialDto;
import com.xian.model.wemedia.pojos.WmMaterial;
import com.xian.wemedia.service.WmMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    /**
     * 删除素材图片
     * @param id
     * @return
     */
    @GetMapping("/del_picture/{id}")
    public ResponseResult delPicture(@PathVariable Integer id){
        //        数据不存在
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE);
        }
        WmMaterial wm = wmMaterialService.getById(id);
        if(wm == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        if(wmMaterialService.removeById(id)){
            return ResponseResult.okResult(wm);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.DEL_FAILD);
    }

    @GetMapping("/collect/{id}")
    public ResponseResult  collect(@PathVariable Integer id){
        if(id == null ){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmMaterial wm = wmMaterialService.getById(id);
        wm.setIsCollection((short) 1);
        wmMaterialService.updateById(wm);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 取消收藏
     * @param id
     * @return
     */
    @GetMapping("/cancel_collect/{id}")
    public ResponseResult cancelCollect(@PathVariable Integer id){
        if(id == null ){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmMaterial wm = wmMaterialService.getById(id);
        wm.setIsCollection((short) 0);
//        更新到数据库
        wmMaterialService.updateById(wm);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);

    }
}
