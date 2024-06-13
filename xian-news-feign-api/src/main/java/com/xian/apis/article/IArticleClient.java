package com.xian.apis.article;

import com.xian.apis.article.fallback.IArticleClientFallback;
import com.xian.model.article.dtos.ArticleDto;
import com.xian.model.common.dtos.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 定义文章端的接口
 * 远程接口指向降级代码fallback = IArticleClientFallback.class
 */
@FeignClient(value = "news-article",fallback = IArticleClientFallback.class)
public interface IArticleClient {

    @PostMapping("/api/v1/article/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto dto) ;
}