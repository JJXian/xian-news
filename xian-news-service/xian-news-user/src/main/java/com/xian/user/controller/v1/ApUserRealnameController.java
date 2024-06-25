package com.xian.user.controller.v1;

import com.xian.common.constants.UserConstants;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.user.dtos.AuthDto;
import com.xian.user.service.ApUserRealnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-25
 */
@RestController
@RequestMapping("/api/v1/auth")
public class ApUserRealnameController {

    @Autowired
    private ApUserRealnameService apUserRealnameService;


    @PostMapping("/list")
    public ResponseResult loadListByStatus(@RequestBody AuthDto dto){
        return apUserRealnameService.loadListByStatus(dto);
    }

    @PostMapping("/authPass")
    public ResponseResult authPass(@RequestBody AuthDto dto ){
        return apUserRealnameService.updateStatus(dto, UserConstants.PASS_AUTH);
    }

    @PostMapping("/authFail")
    public ResponseResult authFail(@RequestBody AuthDto dto ){
        return apUserRealnameService.updateStatus(dto, UserConstants.FAIL_AUTH);
    }
}