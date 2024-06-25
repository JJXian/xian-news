package com.xian.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xian.apis.wemedia.IWemediaClient;
import com.xian.common.constants.UserConstants;
import com.xian.model.admin.pojos.ApUserRealname;
import com.xian.model.common.dtos.PageResponseResult;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.user.dtos.AuthDto;
import com.xian.model.user.pojos.ApUser;
import com.xian.model.wemedia.pojos.WmUser;
import com.xian.user.mapper.ApUserMapper;
import com.xian.user.mapper.ApUserRealnameMapper;
import com.xian.user.service.ApUserRealnameService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-25
 */
@Service
@Slf4j
@Transactional
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {

    /**
     * 根据状态列出
     * @param dto
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(AuthDto dto) {
        //1.检查参数
        if(dto == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //分页条件检查
        dto.checkParam();

        //2.分页根据状态精确查询
        IPage page = new Page(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<ApUserRealname> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(dto.getStatus() != null){
            lambdaQueryWrapper.eq(ApUserRealname::getStatus,dto.getStatus());
        }
        page = page(page,lambdaQueryWrapper);

        //3.结果返回
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int)page.getTotal());
        responseResult.setData(page.getRecords());
        return responseResult;
    }

    /**
     * 更新状态
     * @param dto
     * @param status  2审核失败    9审核成功
     * @return
     */
    @Override
    public ResponseResult updateStatus(AuthDto dto, Short status) {
        //1.检查参数
        if(dto == null || dto.getId() == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }

        //2.修改认证状态
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());
        apUserRealname.setStatus(status);
        if(StringUtils.isNotBlank(dto.getMsg())){
            apUserRealname.setReason(dto.getMsg());
        }
        updateById(apUserRealname);

        //3.如果审核状态是9，就是成功，需要创建自媒体账户
        if(status.equals(UserConstants.PASS_AUTH)){
            ResponseResult responseResult = createWmUserAndAuthor(dto);
            if(responseResult != null){
                return responseResult;
            }
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Autowired
//    @Resource
    private IWemediaClient wemediaClient;

    @Autowired
    private ApUserMapper apUserMapper;

    /**
     * 创建自媒体账户
     * @param dto
     * @return
     */
    private ResponseResult createWmUserAndAuthor(AuthDto dto) {
        Integer userRealnameId = dto.getId();
        //查询用户认证信息
        ApUserRealname apUserRealname = getById(userRealnameId);
        if(apUserRealname == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //查询app端用户信息
        Integer userId = apUserRealname.getUserId();
        ApUser apUser = apUserMapper.selectById(userId);
        if(apUser == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        //创建自媒体账户
        WmUser wmUser = wemediaClient.findWmUserByName(apUser.getName());
        if(wmUser == null){
            wmUser= new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setCreatedTime(new Date());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setPhone(apUser.getPhone());
            wmUser.setStatus(9);
            wemediaClient.saveWmUser(wmUser);
        }
        apUser.setFlag((short)1);
        apUserMapper.updateById(apUser);

        return null;

    }


}
