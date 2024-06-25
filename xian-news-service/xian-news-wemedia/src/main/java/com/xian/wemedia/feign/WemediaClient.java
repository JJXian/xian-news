package com.xian.wemedia.feign;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xian.apis.wemedia.IWemediaClient;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.wemedia.pojos.WmUser;
import com.xian.wemedia.service.WmChannelService;
import com.xian.wemedia.service.WmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-25
 */


@RestController
public class WemediaClient implements IWemediaClient {

    @Autowired
    private WmUserService wmUserService;

    @Override
    @GetMapping("/api/v1/user/findByName/{name}")
    public WmUser findWmUserByName(@PathVariable("name") String name) {
        return wmUserService.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, name));
    }

    @Override
    @PostMapping("/api/v1/wm_user/save")
    public ResponseResult saveWmUser(@RequestBody WmUser wmUser) {
        wmUserService.save(wmUser);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
    private WmChannelService wmChannelService;

    @GetMapping("/api/v1/channel/list")
    @Override
    public ResponseResult getChannels() {
        return wmChannelService.findAll();
    }
}
//
//@RestController
//public class WemediaClient implements IWemediaClient {
//
//    @Autowired
//    private WmUserService wmUserService;
//
//    @GetMapping("/api/v1/user/findByName/{name}")
//    @Override
//    public WmUser findWmUserByName(@PathVariable("name")String name) {
//        return wmUserService.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName,name));
//    }
//
//    @PostMapping("/api/v1/wm_user/save")
//    @Override
//    public ResponseResult saveWmUser(@RequestBody WmUser wmUser) {
//        wmUserService.save(wmUser);
//        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
//    }
//
//    @Autowired
//    private WmChannelService wmChannelService;
//
//    @GetMapping("/api/v1/channel/list")
//    @Override
//    public ResponseResult getChannels() {
//        wmChannelService.findAll();
//        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
//    }
//}
