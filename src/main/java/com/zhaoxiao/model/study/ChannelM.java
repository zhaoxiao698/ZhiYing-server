package com.zhaoxiao.model.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("频道")
public class ChannelM {
    @ApiModelProperty("频道id")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("描述")
    private String info;
    @ApiModelProperty("封面图片地址")
    private String img;
    @ApiModelProperty("包含的文章数量")
    private Integer num;
    @ApiModelProperty("收藏数量")
    private Integer collection;
    @ApiModelProperty("最近更新时间")
    private Date lastTime;
    private boolean collectStatus;

    public ChannelM() {
    }

    public ChannelM(Integer id, String name, String info, String img, Integer num, Integer collection, Date lastTime) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.img = img;
        this.num = num;
        this.collection = collection;
        this.lastTime = lastTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public boolean getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(boolean collectStatus) {
        this.collectStatus = collectStatus;
    }
}
