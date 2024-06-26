package com.xian.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xian.model.wemedia.dtos.NewsAuthDto;
import com.xian.model.wemedia.pojos.WmNews;
import com.xian.model.wemedia.vo.WmNewsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface WmNewsMapper  extends BaseMapper<WmNews> {

    List<WmNewsVo> findListAndPage(@Param("dto") NewsAuthDto dto);

    int findListCount(@Param("dto") NewsAuthDto dto);
}