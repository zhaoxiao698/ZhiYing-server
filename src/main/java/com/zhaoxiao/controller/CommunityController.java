package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
                                         @RequestParam(defaultValue = "-1") int topicId,
                                         @RequestParam(defaultValue = "") String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTrendList(sort,order,fanAccount,topicId,account));
    }

    @GetMapping("/getCommentList")
    public PageInfo<CommentM> getCommentList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             @RequestParam(defaultValue = "0") int sort,
                                             @RequestParam(defaultValue = "false") boolean order,
                                             int trendId,String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getCommentList(sort,order,trendId,account));
    }

    @GetMapping("/getTrend")
    public TrendM getTrend(int trendId,String account){
        return communityService.getTrend(trendId,account);
    }

    @GetMapping("/getTopicList")
    public PageInfo<Topic> getTopicList(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTopicList());
    }

    @GetMapping("/getTopic")
    public Topic getTopic(int topicId,String account){
        return communityService.getTopic(topicId,account);
    }

    @GetMapping("/getTrendCollectionList")
    public PageInfo<TrendM> getTrendCollectionList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTrendCollectionList(account));
    }

    @GetMapping("/getTopicCollectionList")
    public PageInfo<Topic> getTopicCollectionList(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "8") int pageSize,
                                                  String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTopicCollectionList(account));
    }

    @GetMapping("/getTrendHistoryList")
    public PageInfo<TrendM> getTrendHistoryList(@RequestParam(defaultValue = "1") int pageNo,
                                                   @RequestParam(defaultValue = "8") int pageSize,
                                                   String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTrendHistoryList(account));
    }

    @GetMapping("/addTrendRecord")
    public boolean addTrendRecord(String account,int trendId){
        return communityService.addTrendRecord(account, trendId);
    }

    @PostMapping("/publishTrend")
    public boolean publishTrend(@RequestParam("account") String account,
                                @RequestParam("title") String title,
                                @RequestParam("info") String info,
                                @RequestParam("topicIdList") List<Integer> topicIdList,
                                @RequestParam("imgFileList") List<MultipartFile> imgFileList,
                                @RequestParam("linkId") int linkId,
                                @RequestParam("linkType") int linkType,
                                @RequestParam("linkTable") int linkTable){
        return communityService.publishTrend(account, title, info, topicIdList, imgFileList, linkId, linkType, linkTable);
    }

    @GetMapping("/deleteTrend")
    public boolean deleteTrend(int trendId){
        return communityService.deleteTrend(trendId);
    }

    @PostMapping("/createTopic")
    public boolean createTopic(@RequestBody Topic topic){
        return communityService.createTopic(topic);
    }

    @GetMapping("/like")
    public boolean like(String account,int trendId,boolean like){
        return communityService.like(account,trendId,like);
    }

    @GetMapping("/collect")
    public boolean collect(String account,int trendId,boolean collect){
        return communityService.collect(account,trendId,collect);
    }

    @GetMapping("/attention")
    public boolean attention(String userAccount,String fanAccount,boolean attention){
        return communityService.attention(userAccount,fanAccount,attention);
    }

    @GetMapping("/topicCollect")
    public boolean topicCollect(String account,int topicId,boolean collect){
        return communityService.topicCollect(account,topicId,collect);
    }

    @GetMapping("/getTrendSearchList")
    public PageInfo<TrendM> getTrendSearchList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         String searchWord, String account) {
        PageHelper.startPage(pageNo, pageSize);
        return new PageInfo<>(communityService.getTrendSearchList(searchWord, account));
    }

    @GetMapping("/sendComment")
    public boolean sendComment(int trendId,String account,String info) {
        return communityService.sendComment(trendId, account, info);
    }

    @GetMapping("/likeComment")
    public boolean likeComment(int commentId,String account,boolean like) {
        return communityService.likeComment(commentId, account, like);
    }

    @GetMapping("/getTopicSearchList")
    public PageInfo<Topic> getTopicSearchList(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "8") int pageSize,
                                      String searchWord) {
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(communityService.getTopicSearchList(searchWord));
    }
}
