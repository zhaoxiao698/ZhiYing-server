package com.zhaoxiao.entity.mine;

import java.util.Date;

public class Plan {
    private int id;
    private String userAccount;
    private long plan;
    private long planDo;
    private Date addTime;

    public Plan() {
    }

    public Plan(int id, String userAccount, int plan, int planDo, Date addTime) {
        this.id = id;
        this.userAccount = userAccount;
        this.plan = plan;
        this.planDo = planDo;
        this.addTime = addTime;
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

    public long getPlan() {
        return plan;
    }

    public void setPlan(long plan) {
        this.plan = plan;
    }

    public long getPlanDo() {
        return planDo;
    }

    public void setPlanDo(long planDo) {
        this.planDo = planDo;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
