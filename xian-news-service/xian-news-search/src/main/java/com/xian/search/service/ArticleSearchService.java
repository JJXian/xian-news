package com.xian.search.service;

import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.search.dtos.UserSearchDto;

import java.io.IOException;

public interface ArticleSearchService {

    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto userSearchDto) throws IOException;
}