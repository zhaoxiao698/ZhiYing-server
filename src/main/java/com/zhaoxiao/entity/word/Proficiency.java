package com.zhaoxiao.entity.word;

import java.util.Date;

public class Proficiency {
    private String userAccount;
    private String wordId;
    private int proficiency;
    private Date nextTime;
    private String bookId;

    public Proficiency() {
    }

    public Proficiency(String userAccount, String wordId, int proficiency, Date nextTime, String bookId) {
        this.userAccount = userAccount;
        this.wordId = wordId;
        this.proficiency = proficiency;
        this.nextTime = nextTime;
        this.bookId = bookId;
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

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public Date getNextTime() {
        return nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
