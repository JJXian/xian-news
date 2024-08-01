package com.xian.model.article.vos;

import com.xian.model.article.pojos.ApArticle;
import lombok.Data;

/**
 * @Author: jjxian
 */
@Data
public class HotArticleVo  extends ApArticle {
    /**
     * 文章分值
     */
    private Integer score;
}
