package com.zhaoxiao.entity;

import java.util.Date;

public class Channel {
    private Integer id;
    private Integer stypeId;
    private String name;
    private String info;
    private String img;
    private Integer num;
    private Integer collection;
    private Date lastTime;

    public Channel() {
    }

    public Channel(Integer id, Integer stypeId, String name, String info, String img, Integer num, Integer collection, Date lastTime) {
        this.id = id;
        this.stypeId = stypeId;
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

    public Integer getStypeId() {
        return stypeId;
    }

    public void setStypeId(Integer stypeId) {
        this.stypeId = stypeId;
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
}
