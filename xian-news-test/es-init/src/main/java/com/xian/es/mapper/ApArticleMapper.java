package com.xian.es.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xian.model.article.pojos.ApArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApArticleMapper extends BaseMapper<ApArticle> {

    public List<com.xian.es.pojo.SearchArticleVo> loadArticleList();

}
