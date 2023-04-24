package com.zhaoxiao.entity.study;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("二级分类")
public class Stype {
    @ApiModelProperty("分类id")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;

    public Stype() {
    }

    public Stype(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
