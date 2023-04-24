package com.zhaoxiao.entity.community;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("话题")
public class Topic {
    @ApiModelProperty("话题id")
    private int id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("描述")
    private String info;
    @ApiModelProperty("添加时间")
    private Date addTime;
    @ApiModelProperty("添加话题的人的账号")
    private String userAccount;
    @ApiModelProperty("参与人数")
    private int join;
    @ApiModelProperty("收藏数")
    private int collection;

    public Topic() {
    }

    public Topic(int id, String name, String info, Date addTime, String userAccount, int join, int collection) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.addTime = addTime;
        this.userAccount = userAccount;
        this.join = join;
        this.collection = collection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public int getJoin() {
        return join;
    }

    public void setJoin(int join) {
        this.join = join;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }
}
