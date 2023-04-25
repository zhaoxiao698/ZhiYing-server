package com.zhaoxiao.model.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("题库")
public class QuestionBank {
    @ApiModelProperty("题库id")
    private int id;
    @ApiModelProperty("名称")
    private String name;

    public QuestionBank() {
    }

    public QuestionBank(int id, String name) {
        this.id = id;
        this.name = name;
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
}
