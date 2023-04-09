package com.zhaoxiao.entity.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CarefulQuestion extends SubQuestion {
    private int carefulId;

    public CarefulQuestion() {
    }

    public CarefulQuestion(int id, int carefulId, String stem, String a, String b, String c, String d, String answer) {
        super(id, stem, a, b, c, d, answer);
        this.carefulId = carefulId;
    }

    public int getCarefulId() {
        return carefulId;
    }

    public void setCarefulId(int carefulId) {
        this.carefulId = carefulId;
    }
}
