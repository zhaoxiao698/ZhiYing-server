package com.zhaoxiao.model.study;

import com.zhaoxiao.entity.study.Stype;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("一级分类")
public class Ftype {
    @ApiModelProperty("分类id")
    private Integer id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("包含的二级分类列表")
    private List<Stype> stypeList;

    public Ftype() {
    }

    public Ftype(Integer id, String name, List<Stype> stypeList) {
        this.id = id;
        this.name = name;
        this.stypeList = stypeList;
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

    public List<Stype> getStypeList() {
        return stypeList;
    }

    public void setStypeList(List<Stype> stypeList) {
        this.stypeList = stypeList;
    }
}
