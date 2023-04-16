package com.zhaoxiao.entity.word;

import java.util.Date;

public class WordRecord {
    private int id;
    private String userAccount;
    private String wordId;
    private int type;
    private Date addTime;
    private String bookId;

    public WordRecord() {
    }

    public WordRecord(int id, String userAccount, String wordId, int type, Date addTime, String bookId) {
        this.id = id;
        this.userAccount = userAccount;
        this.wordId = wordId;
        this.type = type;
        this.addTime = addTime;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
