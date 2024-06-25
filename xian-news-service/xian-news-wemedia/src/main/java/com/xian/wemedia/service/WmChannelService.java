package com.xian.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.ChannelDto;
import com.xian.model.wemedia.pojos.WmChannel;

public interface WmChannelService extends IService<WmChannel> {

    /**
     * 查询所有频道
     * @return
     */
    public ResponseResult findAll();


    /**
     * delete by id
     * @param id
     * @return
     */
    ResponseResult deleteById(Integer id);

    ResponseResult findByNameAndPage(ChannelDto dto);

    ResponseResult insert(WmChannel adChannel);

    ResponseResult update(WmChannel adChannel);
}
