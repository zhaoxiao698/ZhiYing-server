package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.word.Book;
import com.zhaoxiao.entity.word.LearnedNum;
import com.zhaoxiao.model.word.BookMore;
import com.zhaoxiao.model.word.Word;
import com.zhaoxiao.model.word.WordSimple;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@BaseResponse
@RequestMapping("/word")
public class WordController {
    @Autowired
    private WordService wordService;

    @GetMapping("/getWordList")
    @Deprecated
    public PageInfo<Word> getWordList(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(wordService.getWordList());
    }

    @GetMapping("/getWordSimpleList")
    public PageInfo<WordSimple> getWordSimpleList(@RequestParam(defaultValue = "1") int pageNo,
                                                  @RequestParam(defaultValue = "8") int pageSize,
                                                  String bookId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(wordService.getWordSimpleList(bookId));
    }

    @GetMapping("/getReviewList")
    public PageInfo<WordSimple> getReviewList(String account, @RequestParam(defaultValue = "10") int num,
                                              String bookId){
        return new PageInfo<>(wordService.getReviewList(account,num,bookId));
    }

    @GetMapping("/getLearnList")
    public PageInfo<WordSimple> getLearnList(String account, @RequestParam(defaultValue = "10") int num,
                                             String bookId){
        return new PageInfo<>(wordService.getLearnList(account,num,bookId));
    }

    @GetMapping("/getNeedNum")
    public Map<String, Integer> getNeedNum(String account,
                                           String bookId){
        return wordService.getNeedNum(account,bookId);
    }

    @GetMapping("/addWordRecord")
    public Map<String,Integer> addWordRecord(String account, String wordId, int type, String bookId){
        return wordService.addWordRecord(account,wordId,type,bookId);
    }

    @GetMapping("/getBook")
    public Book getBook(String bookId){
        return wordService.getBook(bookId);
    }

    @GetMapping("/getBookList")
    public PageInfo<Book> getBookList(@RequestParam(defaultValue = "1") int pageNo,
                                          @RequestParam(defaultValue = "8") int pageSize,
                                          String type){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(wordService.getBookList(type));
    }

    @GetMapping("/getLearnedNum")
    public LearnedNum getLearnedNum(String account, String day){
        return wordService.getLearnedNum(account,day);
    }

    @GetMapping("/getBookMore")
    public BookMore getBookMore(String account, String bookId){
        return wordService.getBookMore(account,bookId);
    }

    @GetMapping("/getNewDate")
    public Date getNewDate(){
        return wordService.getNewDate();
    }

    @GetMapping("/getWordSimplePageInfo")
    public PageInfo<WordSimple> getWordSimplePageInfo(@RequestParam(defaultValue = "1") int pageNo,
                                                  @RequestParam(defaultValue = "8") int pageSize,
                                                      String account,String bookId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(wordService.getWordSimplePageInfo(account,bookId));
    }

    @GetMapping("/getCollectionList")
    public PageInfo<WordSimple> getCollectionList(@RequestParam(defaultValue = "1") int pageNo,
                                                  @RequestParam(defaultValue = "8") int pageSize,
                                                  String account,String bookId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(wordService.getCollectionList(account,bookId));
    }

    @GetMapping("/collect")
    public boolean collect(String account,String wordId,String bookId,boolean collect){
        return wordService.collect(account,wordId,bookId,collect);
    }
}
