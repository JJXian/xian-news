package com.xian.behavior.service.impl;

import com.alibaba.fastjson.JSON;
import com.xian.behavior.service.ApUnlikesBehaviorService;
import com.xian.common.constants.BehaviorConstants;
import com.xian.common.redis.CacheService;
import com.xian.model.behavior.dtos.UnLikesBehaviorDto;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.user.pojos.ApUser;
import com.xian.utils.thread.AppThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * APP不喜欢行为表 服务实现类
 *
 * @author jjxian
 */
@Slf4j
@Service
public class ApUnlikesBehaviorServiceImpl implements ApUnlikesBehaviorService {

    @Autowired
    private CacheService cacheService;

    @Override
    public ResponseResult unLike(UnLikesBehaviorDto dto) {

        if (dto.getArticleId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        ApUser user = AppThreadLocalUtil.getUser();
        if (user == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }

        if (dto.getType() == 0) {
            log.info("保存当前key:{} ,{}, {}", dto.getArticleId(), user.getId(), dto);
            cacheService.hPut(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString(), JSON.toJSONString(dto));
        } else {
            log.info("删除当前key:{} ,{}, {}", dto.getArticleId(), user.getId(), dto);
            cacheService.hDelete(BehaviorConstants.UN_LIKE_BEHAVIOR + dto.getArticleId().toString(), user.getId().toString());
        }

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }
}