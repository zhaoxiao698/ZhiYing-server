package com.zhaoxiao.entity.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SubQuestion {
    private int id;
    private String stem;
    @JsonProperty
    private String A;
    @JsonProperty
    private String B;
    @JsonProperty
    private String C;
    @JsonProperty
    private String D;
    private String answer;
    private int order;
    private String userAnswer;

    public SubQuestion() {
    }

    public SubQuestion(int id, String stem, String answer) {
        this.id = id;
        this.stem = stem;
        this.answer = answer;
    }

    public SubQuestion(int id, String stem, String a, String b, String c, String d, String answer) {
        this.id = id;
        this.stem = stem;
        A = a;
        B = b;
        C = c;
        D = d;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStem() {
        return stem;
    }

    public void setStem(String stem) {
        this.stem = stem;
    }

    @JsonIgnore
    public String getA() {
        return A;
    }

    @JsonIgnore
    public void setA(String a) {
        A = a;
    }

    @JsonIgnore
    public String getB() {
        return B;
    }

    @JsonIgnore
    public void setB(String b) {
        B = b;
    }

    @JsonIgnore
    public String getC() {
        return C;
    }

    @JsonIgnore
    public void setC(String c) {
        C = c;
    }

    @JsonIgnore
    public String getD() {
        return D;
    }

    @JsonIgnore
    public void setD(String d) {
        D = d;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }
}
