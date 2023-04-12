package com.zhaoxiao.entity.community;

public class ImageViewInfo {

    private String mUrl;

    private String mDescription = "描述信息";

    public ImageViewInfo(String url) {
        mUrl = url;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getUrl() {//将你的图片地址字段返回
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
}
