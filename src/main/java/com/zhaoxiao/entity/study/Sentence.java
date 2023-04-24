package com.zhaoxiao.entity.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("句子")
public class Sentence {
    @ApiModelProperty("句子id")
    private Integer id;
    @ApiModelProperty("所属文章id")
    private Integer articleId;
    @ApiModelProperty("句子顺序")
    private Integer order;
    @ApiModelProperty("英文")
    private String english;
    @ApiModelProperty("译文")
    private String translation;
    @ApiModelProperty("时间节点")
    private Integer node;
    @ApiModelProperty("是否为段落开头--> 1:是，0:否")
    private Integer first;

    public Sentence() {
    }

    public Sentence(Integer id, Integer articleId, Integer order, String english, String translation, Integer node, Integer first) {
        this.id = id;
        this.articleId = articleId;
        this.order = order;
        this.english = english;
        this.translation = translation;
        this.node = node;
        this.first = first;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public Integer getNode() {
        return node;
    }

    public void setNode(Integer node) {
        this.node = node;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }
}
