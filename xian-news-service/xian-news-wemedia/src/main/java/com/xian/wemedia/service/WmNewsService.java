package com.xian.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.WmNewsDto;
import com.xian.model.wemedia.dtos.WmNewsPageReqDto;
import com.xian.model.wemedia.pojos.WmNews;

public interface WmNewsService extends IService<WmNews> {

    /**
     * 查询文章
     * @param dto
     * @return
     */
    public ResponseResult findAll(WmNewsPageReqDto dto);

    /**
     *  发布文章或保存草稿
     * @param dto
     * @return
     */
    public ResponseResult submitNews(WmNewsDto dto);

}