package com.xian.behavior.service;


import com.xian.model.behavior.dtos.ReadBehaviorDto;
import com.xian.model.common.dtos.ResponseResult;

public interface ApReadBehaviorService {

    /**
     * 保存阅读行为
     * @param dto
     * @return
     */
    public ResponseResult readBehavior(ReadBehaviorDto dto);
}
