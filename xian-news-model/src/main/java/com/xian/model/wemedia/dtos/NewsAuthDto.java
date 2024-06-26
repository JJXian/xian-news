package com.xian.model.wemedia.dtos;

import com.xian.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-26
 */
@Data
public class NewsAuthDto extends PageRequestDto {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 状态
     */
    private Short status;
    /**
     * 自媒体文章id
     */
    private Integer id;
    /**
     * 审核失败的原因
     */
    private String msg;
}
