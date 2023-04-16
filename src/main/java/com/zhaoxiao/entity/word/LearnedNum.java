package com.zhaoxiao.entity.word;

import java.util.Date;

public class LearnedNum {
    Date day;
    int num;

    public LearnedNum() {
    }

    public LearnedNum(Date day, int num) {
        this.day = day;
        this.num = num;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
