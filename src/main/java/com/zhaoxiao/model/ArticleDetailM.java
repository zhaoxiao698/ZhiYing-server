package com.zhaoxiao.model;

import com.zhaoxiao.entity.Sentence;

import java.util.Date;
import java.util.List;

public class ArticleDetailM {
    private Integer id;
    private String title;
    private String duration;
    private String img;
    private String audio;
    private String video;
    private Integer count;
    private Integer collection;
    private Date addTime;
    private List<Sentence> sentenceList;

    public ArticleDetailM() {
    }

    public ArticleDetailM(Integer id, String title, String duration, String img, String audio, String video, Integer count, Integer collection, Date addTime, List<Sentence> sentenceList) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.img = img;
        this.audio = audio;
        this.video = video;
        this.count = count;
        this.collection = collection;
        this.addTime = addTime;
        this.sentenceList = sentenceList;
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

    public List<Sentence> getSentenceList() {
        return sentenceList;
    }

    public void setSentenceList(List<Sentence> sentenceList) {
        this.sentenceList = sentenceList;
    }
}
