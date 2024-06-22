package com.xian.search.service;


import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.search.dtos.UserSearchDto;


/**
 * 联想词表
 */
public interface ApAssociateWordsService {

    /**
     联想词
     @param userSearchDto
     @return
     */
    ResponseResult findAssociate(UserSearchDto userSearchDto);


}
