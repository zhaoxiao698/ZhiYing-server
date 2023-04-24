package com.zhaoxiao.model.mine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("反馈实体")
public class Feedback {
    @ApiModelProperty("反馈id")
    int id;
    @ApiModelProperty("反馈信息")
    String info;
    @ApiModelProperty("反馈的用户的账号")
    String userAccount;
    @ApiModelProperty("反馈处理状态--> 1:已处理，0:未处理")
    boolean handle;

    public Feedback() {
    }

    public Feedback(int id, String info, String userAccount, boolean handle) {
        this.id = id;
        this.info = info;
        this.userAccount = userAccount;
        this.handle = handle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public boolean isHandle() {
        return handle;
    }

    public void setHandle(boolean handle) {
        this.handle = handle;
    }
}
