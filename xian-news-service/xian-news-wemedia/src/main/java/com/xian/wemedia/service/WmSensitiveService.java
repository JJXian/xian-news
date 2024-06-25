package com.xian.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.wemedia.dtos.SensitiveDto;
import com.xian.model.wemedia.pojos.WmSensitive;


public interface WmSensitiveService extends IService<WmSensitive> {

    /**
     * 根据id删除
     * @param id
     * @return
     */
    ResponseResult deleteById(Integer id);

    /**
     * 列表展示
     * @return
     */
    ResponseResult listPage(SensitiveDto dto);

    /**
     * 保存
     * @param sensitive
     * @return
     */
    ResponseResult insert(WmSensitive sensitive);

    /**
     * 修改
     * @param sensitive
     * @return
     */
    ResponseResult update(WmSensitive sensitive);
}
