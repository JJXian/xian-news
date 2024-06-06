package com.xian.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.user.dtos.LoginDto;
import com.xian.model.user.pojos.ApUser;

public interface ApUserService extends IService<ApUser> {
    /**
     * app端登录功能
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
