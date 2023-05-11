package com.zhaoxiao.model.test;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class TestNoteDetail {
    private String userAccount;
    private int questionId;
    private String questionInfo;
    private String subType;
    private String info;
    private Date addTime;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestionInfo() {
        return questionInfo;
    }

    public void setQuestionInfo(String questionInfo) {
        this.questionInfo = questionInfo;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
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
