package com.xian.wemedia.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xian.model.wemedia.pojos.WmUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WmUserMapper extends BaseMapper<WmUser> {
}