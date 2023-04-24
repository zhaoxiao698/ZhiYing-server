package com.zhaoxiao.model.test;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

@ApiModel("一级分类")
public class TestFtype implements Serializable {
    @ApiModelProperty("分类id")
    @SerializedName("id")
    private int id;
    @ApiModelProperty("名称")
    @SerializedName("name")
    private String name;
    @ApiModelProperty("二级分类列表")
    @SerializedName("stypeList")
    private List<TestStype> stypeList;
    @ApiModelProperty("返回false（不用管）")
    @SerializedName("expand")
    private boolean expand;
    @ApiModelProperty("包含的题目数量")
    @SerializedName("num")
    private int num;
    @ApiModelProperty("（不用管）")
    @SerializedName("finish")
    private int finish;
    @ApiModelProperty("（不用管）")
    @SerializedName("right")
    private int right;

    public TestFtype() {
    }

    public TestFtype(int id, String name, List<TestStype> stypeList, boolean expand, int num, int finish, int right) {
        this.id = id;
        this.name = name;
        this.stypeList = stypeList;
        this.expand = expand;
        this.num = num;
        this.finish = finish;
        this.right = right;
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

    public List<TestStype> getStypeList() {
        return stypeList;
    }

    public void setStypeList(List<TestStype> stypeList) {
        this.stypeList = stypeList;
    }

    public boolean getExpand() {
        return expand;
    }

    public void setExpand(boolean expand) {
        this.expand = expand;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Ftype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stypeList=" + stypeList +
                '}';
    }
}
