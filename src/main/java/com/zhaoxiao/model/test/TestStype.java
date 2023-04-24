package com.zhaoxiao.model.test;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("二级分类")
public class TestStype implements Serializable {
    @ApiModelProperty("分类id")
    @SerializedName("id")
    private int id;
    @ApiModelProperty("名称")
    @SerializedName("name")
    private String name;
    @ApiModelProperty("包含的题目数量")
    @SerializedName("num")
    private int num;
    @ApiModelProperty("（不用管）")
    @SerializedName("finish")
    private int finish;
    @ApiModelProperty("（不用管）")
    @SerializedName("right")
    private int right;

    public TestStype() {
    }

    public TestStype(int id, String name, int num, int finish, int right) {
        this.id = id;
        this.name = name;
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
        return "Stype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}