package com.zhaoxiao.model.test;

public class TruePaper {
    private int id;
    private String name;
    private int questionBankId;
    private String questionBankName;

    public TruePaper() {
    }

    public TruePaper(int id, String name, int questionBankId, String questionBankName) {
        this.id = id;
        this.name = name;
        this.questionBankId = questionBankId;
        this.questionBankName = questionBankName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(int questionBankId) {
        this.questionBankId = questionBankId;
    }

    public String getQuestionBankName() {
        return questionBankName;
    }

    public void setQuestionBankName(String questionBankName) {
        this.questionBankName = questionBankName;
    }
}
