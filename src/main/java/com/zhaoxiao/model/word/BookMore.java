package com.zhaoxiao.model.word;

import com.zhaoxiao.entity.word.Book;
import com.zhaoxiao.entity.word.LearnedNum;

public class BookMore {
    private Book book;
    private int reviewNum;
    private int learnNum;
    private LearnedNum learnedNum;

    public BookMore() {
    }

    public BookMore(Book book, int reviewNum, int learnNum, LearnedNum learnedNum) {
        this.book = book;
        this.reviewNum = reviewNum;
        this.learnNum = learnNum;
        this.learnedNum = learnedNum;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getReviewNum() {
        return reviewNum;
    }

    public void setReviewNum(int reviewNum) {
        this.reviewNum = reviewNum;
    }

    public int getLearnNum() {
        return learnNum;
    }

    public void setLearnNum(int learnNum) {
        this.learnNum = learnNum;
    }

    public LearnedNum getLearnedNum() {
        return learnedNum;
    }

    public void setLearnedNum(LearnedNum learnedNum) {
        this.learnedNum = learnedNum;
    }
}
