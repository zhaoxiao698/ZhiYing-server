package com.zhaoxiao.entity;

public class Banner {
    private Integer id;
    private Integer contentId;
    private String img;
    private Integer type;

    public Banner() {
    }

    public Banner(Integer id, Integer contentId, String img, Integer type) {
        this.id = id;
        this.contentId = contentId;
        this.img = img;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
