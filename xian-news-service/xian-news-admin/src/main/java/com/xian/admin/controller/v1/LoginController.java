package com.xian.admin.controller.v1;

import com.xian.admin.service.AdUserService;
import com.xian.model.admin.dtos.AdUserDto;
import com.xian.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-24
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AdUserService adUserService;

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping("in")
    public ResponseResult login(@RequestBody AdUserDto dto){

        return adUserService.login(dto);
    }
}
