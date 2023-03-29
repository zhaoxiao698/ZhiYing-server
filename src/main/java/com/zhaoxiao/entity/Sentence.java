package com.zhaoxiao.entity;

public class Sentence {
    private Integer id;
    private Integer articleId;
    private Integer order;
    private String english;
    private String translation;
    private Integer node;
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
