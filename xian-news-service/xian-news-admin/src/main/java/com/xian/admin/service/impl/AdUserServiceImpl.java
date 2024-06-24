package com.xian.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xian.admin.mapper.AdUserMapper;
import com.xian.admin.service.AdUserService;
import com.xian.model.admin.dtos.AdUserDto;
import com.xian.model.admin.pojos.AdUser;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-24
 */
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {

    /**
     * 登陆
     * @param dto
     * @return
     */
    @Override
    public ResponseResult login(AdUserDto dto) {
        if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码为空");
        }

        // 获取用户
        AdUser dbUser = new AdUser();
        dbUser = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if(dbUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
//        比对密码
        String salt = dbUser.getSalt();
        String password = dto.getPassword();
        String pswd = DigestUtils.md5DigestAsHex((password+salt).getBytes());
//        String pswd = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!pswd.equals(dbUser.getPassword() )){
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        //1.3 返回数据  jwt  user
        String token = AppJwtUtil.getToken(dbUser.getId().longValue());
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        dbUser.setSalt("");
        dbUser.setPassword("");
        map.put("user",dbUser);

        return ResponseResult.okResult(map);
    }
}
