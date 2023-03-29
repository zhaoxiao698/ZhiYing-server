package com.zhaoxiao.model;

import java.util.Date;

public class ArticleM {
    private Integer id;
    private String title;
    private String duration;
    private String img;
    private Integer count;
    private Integer collection;
    private Date addTime;

    public ArticleM() {
    }

    public ArticleM(Integer id, String title, String duration, String img, Integer count, Integer collection, Date addTime) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.img = img;
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
