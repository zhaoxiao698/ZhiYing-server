package com.zhaoxiao.model.mine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("登录")
public class AdminAccount {
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码")
    private String password;

    public AdminAccount() {
    }

    public AdminAccount(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
