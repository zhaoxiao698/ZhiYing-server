package com.zhaoxiao.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminCommunityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "3-社区管理")
@RestController
@BaseResponse
@RequestMapping("/admin/community")
public class AdminCommunityController {
    @Autowired
    private AdminCommunityService adminCommunityService;

    @ApiOperation("获取动态列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
    })
    @GetMapping("/getTrendList")
    public PageInfo<TrendM> getTrendList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminCommunityService.getTrendList());
    }

    @ApiOperation("获取评论列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
            @ApiImplicitParam(name = "trendId",value = "所属动态id",required = true),
    })
    @GetMapping("/getCommentList")
    public PageInfo<CommentM> getCommentList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             int trendId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminCommunityService.getCommentList(trendId));
    }

    @ApiOperation("通过id获取动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "动态id",required = true),
    })
    @GetMapping("/getTrend")
    public TrendM getTrend(int id){
        return adminCommunityService.getTrend(id);
    }

    @ApiOperation("获取话题列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
    })
    @GetMapping("/getTopicList")
    public PageInfo<Topic> getTopicList(@RequestParam(defaultValue = "1") int pageNo,
                                        @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminCommunityService.getTopicList());
    }

    @ApiOperation("通过id获取话题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "话题id",required = true),
    })
    @GetMapping("/getTopic")
    public Topic getTopic(int id){
        return adminCommunityService.getTopic(id);
    }

    @ApiOperation("删除动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "动态id",required = true),
    })
    @GetMapping("/removeTrend")
    public boolean removeTrend(int id){
        return adminCommunityService.removeTrend(id);
    }

    @ApiOperation("删除话题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "话题id",required = true),
    })
    @GetMapping("/removeTopic")
    public boolean removeTopic(int id){
        return adminCommunityService.removeTopic(id);
    }
}
