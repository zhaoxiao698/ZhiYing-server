package com.zhaoxiao.model.community;

import java.util.Date;

public class CommentM {
    private int id;
    private int trendId;
    private String userAccount;
    private String userName;
    private String userAvatar;
    private String info;
    private Date addTime;
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
