package com.zhaoxiao.model.mine;

import com.zhaoxiao.entity.mine.Plan;

import java.util.List;

public class CalendarInfo {
    private int continuous;
    private int total;
    private List<Plan> planList;

    public CalendarInfo() {
    }

    public CalendarInfo(int continuous, int total, List<Plan> planList) {
        this.continuous = continuous;
        this.total = total;
        this.planList = planList;
    }

    public int getContinuous() {
        return continuous;
    }

    public void setContinuous(int continuous) {
        this.continuous = continuous;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
    }
}
