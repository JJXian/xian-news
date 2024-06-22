package com.xian.search.controller.v1;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.search.dtos.HistorySearchDto;
import com.xian.search.service.ApUserSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-22
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/history")
public class ApUserSearchController {

    @Autowired
    ApUserSearchService apUserSearchService;
    /**
     * 加载搜索记录列表
     * @return
     */
    @PostMapping("/load")
    public ResponseResult findUserSearch() {
        return apUserSearchService.findUserSearch();
    }


    /**
     * 删除浏览记录
     * @param historySearchDto
     * @return
     */
    @PostMapping("/del")
    public ResponseResult delUserSearch(@RequestBody HistorySearchDto historySearchDto) {
        return apUserSearchService.delUserSearch(historySearchDto);
    }
}
