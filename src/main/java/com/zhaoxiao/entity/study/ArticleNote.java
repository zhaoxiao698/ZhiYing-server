package com.zhaoxiao.entity.study;

import java.util.Date;

public class ArticleNote {
    private String userAccount;
    private int articleId;
    private String info;
    private Date addTime;

    public ArticleNote() {
    }

    public ArticleNote(String userAccount, int articleId, String info, Date addTime) {
        this.userAccount = userAccount;
        this.articleId = articleId;
        this.info = info;
        this.addTime = addTime;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
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
}
