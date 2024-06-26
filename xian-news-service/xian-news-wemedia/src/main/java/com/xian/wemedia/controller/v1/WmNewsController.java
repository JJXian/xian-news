package com.xian.wemedia.controller.v1;

import com.xian.common.constants.WemediaConstants;
import com.xian.model.common.dtos.ResponseResult;
import com.xian.model.common.enums.AppHttpCodeEnum;
import com.xian.model.wemedia.dtos.NewsAuthDto;
import com.xian.model.wemedia.dtos.WmNewsDto;
import com.xian.model.wemedia.dtos.WmNewsPageReqDto;
import com.xian.model.wemedia.pojos.WmNews;
import com.xian.wemedia.service.WmNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: jjxian
 * @CreateTime: 2024-06-09
 */

@RestController
@RequestMapping("/api/v1/news")
public class WmNewsController {

    @Autowired
    WmNewsService wmNewsService;

    @PostMapping("/list")
    public ResponseResult findAll(@RequestBody WmNewsPageReqDto dto){
        return  wmNewsService.findAll(dto);
    }

    @PostMapping("/submit")
    public ResponseResult submitNews(@RequestBody WmNewsDto dto){
        return  wmNewsService.submitNews(dto);
    }

    /**
     * 查询文章列表
     * @param dto
     * @return
     */
    @PostMapping("/list_vo")
    public ResponseResult findList(@RequestBody NewsAuthDto dto){
        return wmNewsService.findList(dto);
    }

    /**
     * 查询文章详情
     * @return
     */
    @GetMapping("/one_vo/{id}")
    public ResponseResult newsDetail(@PathVariable("id") Integer id){
        return wmNewsService.newsDetail(id);
    }

    /**
     * 人工审核通过
     * @param dto
     * @return
     */
    @PostMapping("/auth_pass")
    public ResponseResult authPass(@RequestBody NewsAuthDto dto){
        return wmNewsService.updateStatus(WemediaConstants.WM_NEWS_AUTH_PASS,dto);
    }

    /**
     * 人工审核失败
     * @param dto
     * @return
     */
    @PostMapping("/auth_fail")
    public ResponseResult authFail(@RequestBody NewsAuthDto dto){
        return wmNewsService.updateStatus(WemediaConstants.WM_NEWS_AUTH_FAIL,dto);
    }

    /**
     * 查看文章详情
     * @param id
     * @return
     */
    @GetMapping("/one/{id}")
    public ResponseResult collect(@PathVariable Integer id) {
        return wmNewsService.findOne(id);
        //        if (id == null)
//            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
//        WmNews wmNews = wmNewsService.getById(id);
//        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }



    @GetMapping("/del_news/{id}")
    public ResponseResult deleteNews(@PathVariable Integer id){
        if(id==null)
            return ResponseResult.errorResult(501,"文章Id不可缺少");
        WmNews wmNews = wmNewsService.getById(id);

        if(wmNews==null){
            return ResponseResult.errorResult(1002,"文章不存在");
        }
        if(wmNews.getStatus()==9){
            return ResponseResult.errorResult(501,"文章已发布，不能删除");
        }
        wmNewsService.removeById(id);
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    /**
     * 文章上下架
     * @param dto
     * @return
     */
    @PostMapping("/down_or_up")
    public ResponseResult downOrUp(@RequestBody WmNewsDto dto){
        return wmNewsService.downOrUp(dto);
//        //1.检查参数
//        Integer id=dto.getId();
//        if(id==null){
//            return ResponseResult.errorResult(501,"文章Id不可缺少");
//        }
//        WmNews wmNews =new WmNews();
//        BeanUtils.copyProperties(dto,wmNews);
//        wmNews = wmNewsService.getById(id);
////        System.out.println("----------"+wmNews);
//
//        if(wmNews==null){
//            return ResponseResult.errorResult(1002,"文章不存在");
//        }
//        if(wmNews.getStatus()!=9){// 状态 9为发布
//            return ResponseResult.errorResult(501,"当前文章不是发布状态，不能上下架");
//        }
//        if(wmNews.getEnable()==0){// 0 下架  1 上架
//            wmNews.setEnable((short) 1);
//        }else if(wmNews.getEnable()==1){
//            wmNews.setEnable((short) 0);
//        }
//        wmNewsService.updateById(wmNews);
//        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }


}
