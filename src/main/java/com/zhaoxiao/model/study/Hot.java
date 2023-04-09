package com.zhaoxiao.model.study;

import java.util.Date;

public class Hot {
    private Integer id;
    private String ftypeName;
    private String stypeName;
    private String name;
    private String img;
    private Integer num;
    private Integer collection;
    private Date lastTime;

    public Hot() {
    }

    public Hot(Integer id, String ftypeName, String stypeName, String name, String img, Integer num, Integer collection, Date lastTime) {
        this.id = id;
        this.ftypeName = ftypeName;
        this.stypeName = stypeName;
        this.name = name;
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

    public String getFtypeName() {
        return ftypeName;
    }

    public void setFtypeName(String ftypeName) {
        this.ftypeName = ftypeName;
    }

    public String getStypeName() {
        return stypeName;
    }

    public void setStypeName(String stypeName) {
        this.stypeName = stypeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
