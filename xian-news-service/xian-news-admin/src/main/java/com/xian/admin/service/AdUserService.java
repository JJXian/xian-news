package com.xian.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.admin.dtos.AdUserDto;
import com.xian.model.admin.pojos.AdUser;
import com.xian.model.common.dtos.ResponseResult;

public interface AdUserService extends IService<AdUser> {

    /**
     * 登陆
     * @param dto
     * @return
     */
    ResponseResult login(AdUserDto dto);
}
