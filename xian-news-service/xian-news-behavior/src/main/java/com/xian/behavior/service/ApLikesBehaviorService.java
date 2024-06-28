package com.xian.behavior.service;

import com.xian.model.behavior.dtos.LikesBehaviorDto;
import com.xian.model.common.dtos.ResponseResult;

public interface ApLikesBehaviorService {

    /**
     * 存储喜欢数据
     * @param dto
     * @return
     */
    public ResponseResult like(LikesBehaviorDto dto);
}
