package com.xian.model.wemedia.vo;

import com.xian.model.wemedia.pojos.WmNews;
import lombok.Data;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-26
 */
@Data
public class WmNewsVo extends WmNews {
    /**
     * 作者名称
     */
    private String authorName;
}
