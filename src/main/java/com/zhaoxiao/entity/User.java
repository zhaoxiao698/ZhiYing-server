package com.zhaoxiao.entity;

import java.util.Date;

public class User {
    private String account;
    private String password;
    private String phone;
    private String name;
    private Integer age;
    private String sex;
    private Integer admin;
    private Date addTime;

    public User() {
    }

    public User(String account, String password, String phone, String name, Integer age, String sex, Integer admin, Date addTime) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.admin = admin;
        this.addTime = addTime;
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

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
