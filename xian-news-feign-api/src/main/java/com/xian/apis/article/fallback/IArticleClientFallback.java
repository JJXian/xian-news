package com.xian.apis.article.fallback;

import com.xian.apis.article.IArticleClient;
import com.xian.model.article.dtos.ArticleDto;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import org.springframework.stereotype.Component;

/**
 *
 * feign失败配置
 * @author itheima
 */
@Component
public class IArticleClientFallback implements IArticleClient {
    @Override
    public ResponseResult saveArticle(ArticleDto dto)  {
        return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"获取数据失败");
    }
}