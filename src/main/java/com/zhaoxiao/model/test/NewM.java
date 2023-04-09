package com.zhaoxiao.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhaoxiao.entity.test.NewQuestion;

import java.util.List;

public class NewM extends QuestionM {
    @JsonProperty
    private String A;
    @JsonProperty
    private String B;
    @JsonProperty
    private String C;
    @JsonProperty
    private String D;
    @JsonProperty
    private String E;
    @JsonProperty
    private String F;
    @JsonProperty
    private String G;
    private int type;
    private List<NewQuestion> newQuestionList;

    public NewM() {
    }

    public NewM(int id, String info, String a, String b, String c, String d, String e, String f, String g, int type, int subQuestionNum, List<NewQuestion> newQuestionList) {
        super(id, info, subQuestionNum);
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        this.type = type;
        this.newQuestionList = newQuestionList;
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

    @JsonIgnore
    public String getE() {
        return E;
    }

    @JsonIgnore
    public void setE(String e) {
        E = e;
    }

    @JsonIgnore
    public String getF() {
        return F;
    }

    @JsonIgnore
    public void setF(String f) {
        F = f;
    }

    @JsonIgnore
    public String getG() {
        return G;
    }

    @JsonIgnore
    public void setG(String g) {
        G = g;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<NewQuestion> getNewQuestionList() {
        return newQuestionList;
    }

    public void setNewQuestionList(List<NewQuestion> newQuestionList) {
        this.newQuestionList = newQuestionList;
    }
}
