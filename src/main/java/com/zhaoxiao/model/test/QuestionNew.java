package com.zhaoxiao.model.test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zhaoxiao.entity.test.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("父题目实体（不同类型的题目会返回不同的属性，其他会返回空）")
public class QuestionNew {
    @ApiModelProperty("题目id")
    private int id;
    @ApiModelProperty("题目内容")
    private String info;
    @ApiModelProperty("子题目数量")
    private int subQuestionNum;
    @ApiModelProperty("题目序号")
    private int order;
    @ApiModelProperty("所属题库id")
    private int questionBankId;

    @ApiModelProperty("单词列表--一个字符串用逗号分割（选词填空题才有）")
    private String word;
    @ApiModelProperty("单词列表--一个字符串数组（选词填空题才有）")
    private String[] wordList;
    @ApiModelProperty("子题目列表")
    private List<SubQuestionNew> subQuestionNewList;

    @ApiModelProperty("音频（听力题才有）")
    private String audio;
    @ApiModelProperty("所属类型id（这里是指二级分类）")
    private int type;

    @ApiModelProperty("段落数量（匹配题才有）")
    private int num;

    @ApiModelProperty("A选项内容")
    @JsonProperty
    private String A;
    @ApiModelProperty("B选项内容")
    @JsonProperty
    private String B;
    @ApiModelProperty("C选项内容")
    @JsonProperty
    private String C;
    @ApiModelProperty("D选项内容")
    @JsonProperty
    private String D;
    @ApiModelProperty("E选项内容")
    @JsonProperty
    private String E;
    @ApiModelProperty("F选项内容")
    @JsonProperty
    private String F;
    @ApiModelProperty("G选项内容")
    @JsonProperty
    private String G;

    @ApiModelProperty("答案（翻译题和写作题才有）")
    private String answer;

    @ApiModelProperty("图片地址（写作题才有）")
    private String img;

    public QuestionNew() {
    }

    public QuestionNew(int id, String info, int subQuestionNum, int order, int questionBankId, String word, String[] wordList, List<SubQuestionNew> subQuestionNewList, String audio, int type, int num, String a, String b, String c, String d, String e, String f, String g, String answer, String img) {
        this.id = id;
        this.info = info;
        this.subQuestionNum = subQuestionNum;
        this.order = order;
        this.questionBankId = questionBankId;
        this.word = word;
        this.wordList = wordList;
        this.subQuestionNewList = subQuestionNewList;
        this.audio = audio;
        this.type = type;
        this.num = num;
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        this.answer = answer;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getSubQuestionNum() {
        return subQuestionNum;
    }

    public void setSubQuestionNum(int subQuestionNum) {
        this.subQuestionNum = subQuestionNum;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getQuestionBankId() {
        return questionBankId;
    }

    public void setQuestionBankId(int questionBankId) {
        this.questionBankId = questionBankId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String[] getWordList() {
        return wordList;
    }

    public void setWordList(String[] wordList) {
        this.wordList = wordList;
    }

    public List<SubQuestionNew> getSubQuestionNewList() {
        return subQuestionNewList;
    }

    public void setSubQuestionNewList(List<SubQuestionNew> subQuestionNewList) {
        this.subQuestionNewList = subQuestionNewList;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getE() {
        return E;
    }

    public void setE(String e) {
        E = e;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getG() {
        return G;
    }

    public void setG(String g) {
        G = g;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
