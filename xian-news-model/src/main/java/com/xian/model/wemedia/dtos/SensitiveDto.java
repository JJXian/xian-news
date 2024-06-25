package com.xian.model.wemedia.dtos;

import com.xian.model.common.dtos.PageRequestDto;
import lombok.Data;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-25
 */
@Data
public class SensitiveDto extends PageRequestDto {
    /**
     * 敏感词名称
     */

    String name;


}
