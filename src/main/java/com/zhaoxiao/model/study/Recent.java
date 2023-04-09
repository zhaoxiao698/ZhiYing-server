package com.zhaoxiao.model.study;

import java.util.Date;

public class Recent {
    private Integer id;
    private String channelName;
    private String title;
    private String duration;
    private String img;
    private Date addTime;

    public Recent() {
    }

    public Recent(Integer id, String channelName, String title, String duration, String img, Date addTime) {
        this.id = id;
        this.channelName = channelName;
        this.title = title;
        this.duration = duration;
        this.img = img;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
