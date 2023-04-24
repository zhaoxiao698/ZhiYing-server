package com.zhaoxiao.entity.mine;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("用户实体")
public class User {
    @ApiModelProperty("账号")
    private String account;
    @ApiModelProperty("密码（不会返回也不用显示）")
    private String password;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("头像地址")
    private String avatar;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性别")
    private String sex;
    @ApiModelProperty("权限--> 0:普通用户，1:管理员，2:超级管理员")
    private Integer permissions;
    @ApiModelProperty("创建时间")
    private Date addTime;
    @ApiModelProperty("状态--> 0:冻结，1:可用")
    private Integer status;

    public User() {
    }

    public User(String account, String password, String phone, String name, String avatar, Integer age, String sex, Integer permissions, Date addTime, Integer status) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.age = age;
        this.sex = sex;
        this.permissions = permissions;
        this.addTime = addTime;
        this.status = status;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
