package com.xian.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.article.dtos.ArticleDto;
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

    ResponseResult saveArticle(ArticleDto dto);

    /**
     * 加载文章列表
     * @param dto
     * @param type  1 加载更多   2 加载最新
     * @param firstPage  true  是首页  flase 非首页
     * @return
     */
    public ResponseResult load2(ArticleHomeDto dto,Short type,boolean firstPage);
}