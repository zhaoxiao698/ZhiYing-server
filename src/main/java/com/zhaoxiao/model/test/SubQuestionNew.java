package com.zhaoxiao.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("子题目实体（不同类型的题目会返回不同的属性，其他会返回空）")
public class SubQuestionNew {
    @ApiModelProperty("题目id")
    private int id;
    @ApiModelProperty("题干")
    private String stem;
    @ApiModelProperty("A选项")
    @JsonProperty
    private String A;
    @ApiModelProperty("B选项")
    @JsonProperty
    private String B;
    @ApiModelProperty("C选项")
    @JsonProperty
    private String C;
    @ApiModelProperty("D选项")
    @JsonProperty
    private String D;
    @ApiModelProperty("答案")
    private String answer;
    @ApiModelProperty("序号")
    private int order;
    @ApiModelProperty("用户答案（返回的是空，不用管）")
    private String userAnswer;

    private int superQuestionId;

    private int type;

    public SubQuestionNew() {
    }

    public SubQuestionNew(int id, String stem, String a, String b, String c, String d, String answer, int order, String userAnswer, int superQuestionId, int type) {
        this.id = id;
        this.stem = stem;
        A = a;
        B = b;
        C = c;
        D = d;
        this.answer = answer;
        this.order = order;
        this.userAnswer = userAnswer;
        this.superQuestionId = superQuestionId;
        this.type = type;
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

    public int getSuperQuestionId() {
        return superQuestionId;
    }

    public void setSuperQuestionId(int superQuestionId) {
        this.superQuestionId = superQuestionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
