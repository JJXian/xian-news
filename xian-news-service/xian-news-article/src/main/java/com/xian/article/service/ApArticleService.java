package com.xian.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.article.dtos.ArticleHomeDto;
import com.xian.model.article.pojos.ApArticle;
import com.xian.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {

    /**
     * 根据参数加载文章列表
     * @param loadtype 1为加载更多  2为加载最新
     * @param dto
     * @return
     */
    ResponseResult load(Short loadtype, ArticleHomeDto dto);

}