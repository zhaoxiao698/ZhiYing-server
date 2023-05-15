package com.zhaoxiao.model.community;

import com.zhaoxiao.entity.community.ImageViewInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.entity.mine.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel("动态")
public class TrendM {
    @ApiModelProperty("动态id")
    private int id;
    @ApiModelProperty("发布动态的人的账号")
    private String userAccount;
    @ApiModelProperty("发布动态的人的名字")
    private String userName;
    @ApiModelProperty("发布动态的人的头像地址")
    private String userAvatar;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("内容")
    private String info;
    @ApiModelProperty("（不用管）")
    private List<Topic> topicList;
    @ApiModelProperty("（不用管）")
    private List<User> userList;
    @ApiModelProperty("图片列表")
    private List<ImageViewInfo> imgList;
    @ApiModelProperty("添加时间")
    private Date addTime;
    @ApiModelProperty("点赞量")
    private int like;
    @ApiModelProperty("收藏量")
    private int collection;
    @ApiModelProperty("评论数")
    private int comment;
    @ApiModelProperty("分享量")
    private int share;
    @ApiModelProperty("（不用管）")
    private CommentM hotComment;
    @ApiModelProperty("（不用管）")
    private Date historyTime;
    private int linkId;
    private int linkType;
    private int linkTable;
    private String linkTitle;
    private String linkTypeS;
    private String channelName;
    private String articleImg;
    private String subType;
    private boolean likeStatus;
    private boolean collectStatus;
    private boolean attentionStatus;

    public TrendM() {
    }

    public TrendM(int id, String userAccount, String userName, String userAvatar, String title, String info, List<Topic> topicList, List<User> userList, List<ImageViewInfo> imgList, Date addTime, int like, int collection, int comment, int share, CommentM hotComment) {
        this.id = id;
        this.userAccount = userAccount;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.title = title;
        this.info = info;
        this.topicList = topicList;
        this.userList = userList;
        this.imgList = imgList;
        this.addTime = addTime;
        this.like = like;
        this.collection = collection;
        this.comment = comment;
        this.share = share;
        this.hotComment = hotComment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<ImageViewInfo> getImgList() {
        return imgList;
    }

    public void setImgList(List<ImageViewInfo> imgList) {
        this.imgList = imgList;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCollection() {
        return collection;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public CommentM getHotComment() {
        return hotComment;
    }

    public void setHotComment(CommentM hotComment) {
        this.hotComment = hotComment;
    }

    public Date getHistoryTime() {
        return historyTime;
    }

    public void setHistoryTime(Date historyTime) {
        this.historyTime = historyTime;
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public int getLinkType() {
        return linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public int getLinkTable() {
        return linkTable;
    }

    public void setLinkTable(int linkTable) {
        this.linkTable = linkTable;
    }

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkTypeS() {
        return linkTypeS;
    }

    public void setLinkTypeS(String linkTypeS) {
        this.linkTypeS = linkTypeS;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public boolean getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(boolean collectStatus) {
        this.collectStatus = collectStatus;
    }

    public boolean getAttentionStatus() {
        return attentionStatus;
    }

    public void setAttentionStatus(boolean attentionStatus) {
        this.attentionStatus = attentionStatus;
    }
}
