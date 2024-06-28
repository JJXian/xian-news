package com.xian.behavior.service;


import com.xian.model.behavior.dtos.UnLikesBehaviorDto;
import com.xian.model.common.dtos.ResponseResult;

/**
 * <p>
 * APP不喜欢行为表 服务类
 * </p>
 *
 * @author itheima
 */
public interface ApUnlikesBehaviorService {

    /**
     * 不喜欢
     * @param dto
     * @return
     */
    public ResponseResult unLike(UnLikesBehaviorDto dto);

}