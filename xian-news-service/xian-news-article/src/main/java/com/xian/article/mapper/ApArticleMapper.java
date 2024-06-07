package com.xian.article.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xian.model.article.dtos.ArticleHomeDto;
import com.xian.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<ApArticle> loadArticleList(@Param("dto") ArticleHomeDto dto, @Param("type") Short type);

}