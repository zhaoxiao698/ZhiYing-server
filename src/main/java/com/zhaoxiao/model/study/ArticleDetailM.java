package com.zhaoxiao.model.study;

import com.zhaoxiao.entity.study.Sentence;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("文章详情")
public class ArticleDetailM {
    @ApiModelProperty("文章id")
    private Integer id;
    @ApiModelProperty("所属频道id")
    private Integer channelId;
    @ApiModelProperty("所属频道名称")
    private String channelName;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("音频或视频的时长")
    private String duration;
    @ApiModelProperty("封面图片地址")
    private String img;
    @ApiModelProperty("音频地址")
    private String audio;
    @ApiModelProperty("视频地址")
    private String video;
    @ApiModelProperty("播放量")
    private Integer count;
    @ApiModelProperty("收藏量")
    private Integer collection;
    @ApiModelProperty("添加时间")
    private Date addTime;
    @ApiModelProperty("包含的句子列表")
    private List<Sentence> sentenceList;
    @ApiModelProperty("（不用管）")
    private boolean collect;

    public ArticleDetailM() {
    }

    public ArticleDetailM(Integer id, Integer channelId, String channelName, String title, String duration, String img, String audio, String video, Integer count, Integer collection, Date addTime, List<Sentence> sentenceList) {
        this.id = id;
        this.channelId = channelId;
        this.channelName = channelName;
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

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
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

    public boolean getCollect() {
        return collect;
    }

    public void setCollect(boolean collect) {
        this.collect = collect;
    }
}
