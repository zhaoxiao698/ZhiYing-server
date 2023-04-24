package com.zhaoxiao.model.community;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("评论")
public class CommentM {
    @ApiModelProperty("评论id")
    private int id;
    @ApiModelProperty("所属动态id")
    private int trendId;
    @ApiModelProperty("评论的人的账号")
    private String userAccount;
    @ApiModelProperty("评论的人的名字")
    private String userName;
    @ApiModelProperty("评论的人的头像地址")
    private String userAvatar;
    @ApiModelProperty("评论内容")
    private String info;
    @ApiModelProperty("评论时间")
    private Date addTime;
    @ApiModelProperty("点赞量")
    private int like;

    public CommentM() {
    }

    public CommentM(int id, int trendId, String userAccount, String userName, String userAvatar, String info, Date addTime, int like) {
        this.id = id;
        this.trendId = trendId;
        this.userAccount = userAccount;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.info = info;
        this.addTime = addTime;
        this.like = like;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrendId() {
        return trendId;
    }

    public void setTrendId(int trendId) {
        this.trendId = trendId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
