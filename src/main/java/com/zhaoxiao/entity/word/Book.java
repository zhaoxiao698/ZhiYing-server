package com.zhaoxiao.entity.word;

public class Book {
    private String id;
    private String name;
    private String path;
    private int num;
    private String img;
    private String type;

    public Book() {
    }

    public Book(String id, String name, String path, int num, String img, String type) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.num = num;
        this.img = img;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
