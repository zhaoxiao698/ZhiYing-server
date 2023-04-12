package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @GetMapping("/getTrendList")
    public PageInfo<TrendM> getTrendList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         @RequestParam(defaultValue = "0") int sort,
                                         @RequestParam(defaultValue = "false") boolean order,
                                         @RequestParam(defaultValue = "") String fanAccount,
                                         @RequestParam(defaultValue = "-1") int topicId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTrendList(sort,order,fanAccount,topicId));
    }

    @GetMapping("/getCommentList")
    public PageInfo<CommentM> getCommentList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             @RequestParam(defaultValue = "0") int sort,
                                             @RequestParam(defaultValue = "false") boolean order,
                                             int trendId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getCommentList(sort,order,trendId));
    }

    @GetMapping("/getTrend")
    public TrendM getTrend(int trendId){
        return communityService.getTrend(trendId);
    }

    @GetMapping("/getTopicList")
    public PageInfo<Topic> getTopicList(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTopicList());
    }

    @GetMapping("/getTopic")
    public Topic getTopic(int topicId){
        return communityService.getTopic(topicId);
    }
}
