package com.zhaoxiao.model.mine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录返回")
public class AdminLogin {
    @ApiModelProperty("是否登录成功")
    private boolean login;
    @ApiModelProperty("用户权限（1为管理员，2为超级管理员）")
    private int permissions;
    @ApiModelProperty("提示消息")
    private String message;

    public AdminLogin() {
    }

    public AdminLogin(boolean login, int permissions, String message) {
        this.login = login;
        this.permissions = permissions;
        this.message = message;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getPermissions() {
        return permissions;
    }

    public void setPermissions(int permissions) {
        this.permissions = permissions;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
