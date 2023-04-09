package com.zhaoxiao.entity.study;

import java.util.Date;

public class Article {
    private Integer id;
    private Integer channelId;
    private String title;
    private String duration;
    private String img;
    private String audio;
    private String video;
    private Integer count;
    private Integer collection;
    private Date addTime;

    public Article() {
    }

    public Article(Integer id, Integer channelId, String title, String duration, String img, String audio, String video, Integer count, Integer collection, Date addTime) {
        this.id = id;
        this.channelId = channelId;
        this.title = title;
        this.duration = duration;
        this.img = img;
        this.audio = audio;
        this.video = video;
        this.count = count;
        this.collection = collection;
        this.addTime = addTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCollection() {
        return collection;
    }

    public void setCollection(Integer collection) {
        this.collection = collection;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
