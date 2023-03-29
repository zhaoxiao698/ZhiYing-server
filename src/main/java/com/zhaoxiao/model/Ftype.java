package com.zhaoxiao.model;

import com.zhaoxiao.entity.Stype;

import java.util.List;

public class Ftype {
    private Integer id;
    private String name;
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
