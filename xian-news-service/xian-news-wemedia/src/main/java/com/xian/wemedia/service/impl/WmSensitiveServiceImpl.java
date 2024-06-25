package com.xian.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xian.model.common.dtos.PageResponseResult;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.wemedia.dtos.SensitiveDto;
import com.xian.model.wemedia.pojos.WmSensitive;
import com.xian.wemedia.mapper.WmSensitiveMapper;
import com.xian.wemedia.service.WmSensitiveService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-24
 */
@Service
@Transactional
public class WmSensitiveServiceImpl extends ServiceImpl<WmSensitiveMapper, WmSensitive> implements WmSensitiveService {


    /**
     * 根据id删除
     * @param id
     * @return
     */
    @Override
    public ResponseResult deleteById(Integer id) {
        if(id ==null){
            return  ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        WmSensitive wmSensitive = getById(id);
        if(wmSensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);

        }

        removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 查询
     * @param dto
     * @return
     */
    @Override
    public ResponseResult listPage(SensitiveDto dto) {

        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        dto.checkParam();

        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<WmSensitive> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        if(StringUtils.isNotBlank(dto.getName())){
            lambdaQueryWrapper.like(WmSensitive::getSensitives,dto.getName());
        }

        page = page(page,lambdaQueryWrapper);

        //3.结果返回
        ResponseResult responseResult = new PageResponseResult(dto.getPage(),dto.getSize(),(int)page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * 保存
     * @param sensitive
     * @return
     */
    @Override
    public ResponseResult insert(WmSensitive sensitive) {
        if(null == sensitive){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        WmSensitive dbsensitive = getOne(Wrappers.<WmSensitive>lambdaQuery().eq(WmSensitive::getSensitives,sensitive.getSensitives()));
        if(dbsensitive!=null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST,"敏感词已经存在");
        }

        sensitive.setCreatedTime(new Date());
        save(sensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 修改
     * @param sensitive
     * @return
     */
    @Override
    public ResponseResult update(WmSensitive sensitive) {

        if(sensitive == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

//        WmSensitive wmSensitive = getOne(Wrappers.<WmSensitive>lambdaQuery().eq(WmSensitive::getSensitives,sensitive.getSensitives()));
//
//        if(wmSensitive == null){
//            insert(sensitive);
////            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"敏感词")
//        }
        updateById(sensitive);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}
