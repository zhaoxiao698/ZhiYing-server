package com.zhaoxiao.entity.community;

import java.util.Date;

public class Trend {
    int id;
    String userAccount;
    String title;
    String info;
    Date addTime;
    int like;
    int collection;
    int comment;
    int share;
    int linkId;
    int linkType;
    int linkTable;

    public Trend() {
    }

    public Trend(int id, String userAccount, String title, String info, Date addTime, int like, int collection, int comment, int share, int linkId, int linkType, int linkTable) {
        this.id = id;
        this.userAccount = userAccount;
        this.title = title;
        this.info = info;
        this.addTime = addTime;
        this.like = like;
        this.collection = collection;
        this.comment = comment;
        this.share = share;
        this.linkId = linkId;
        this.linkType = linkType;
        this.linkTable = linkTable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public int getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(int linkTable) {
        this.linkTable = linkTable;
    }
}
