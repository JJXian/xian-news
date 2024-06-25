package com.xian.apis.wemedia;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "news-wemedia")
public interface IWemediaClient {


    @GetMapping("/api/v1/user/findByName/{name}")
    WmUser findWmUserByName(@PathVariable("name") String name);

    @PostMapping("/api/v1/wm_user/save")
    ResponseResult saveWmUser(@RequestBody WmUser wmUser);

    @GetMapping("/api/v1/channel/list")
    public ResponseResult getChannels();
}
