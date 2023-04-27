package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.model.test.*;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/testGet")
    public String testGet(int id){
        return "id="+id;
    }

    @GetMapping("/getListeningList")
    public List<ListeningM> getListeningList(@RequestParam(defaultValue = "false") boolean random,
                                         @RequestParam(defaultValue = "0") int limitNum,
                                         int questionBankId, int num,int type){
        return testService.getListeningList(random,limitNum,questionBankId,num,type);
    }

    @GetMapping("/getBankedList")
    public List<BankedM> getBankedList(@RequestParam(defaultValue = "false") boolean random,
                                       @RequestParam(defaultValue = "0") int limitNum,
                                       int questionBankId){
        return testService.getBankedList(random, limitNum,questionBankId);
    }

    @GetMapping("/getMatchList")
    public List<MatchM> getMatchList(@RequestParam(defaultValue = "false") boolean random,
                                      @RequestParam(defaultValue = "0") int limitNum,
                                      int questionBankId){
        return testService.getMatchList(random, limitNum,questionBankId);
    }

    @GetMapping("/getCarefulList")
    public List<CarefulM> getCarefulList(@RequestParam(defaultValue = "false") boolean random,
                                       @RequestParam(defaultValue = "0") int limitNum,
                                       int questionBankId){
        return testService.getCarefulList(random, limitNum,questionBankId);
    }

    @GetMapping("/getTranslationList")
    public List<TranslationM> getTranslationList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId){
        return testService.getTranslationList(random, limitNum,questionBankId);
    }

    @GetMapping("/getWritingList")
    public List<WritingM> getWritingList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int type){
        return testService.getWritingList(random, limitNum,questionBankId,type);
    }

    @GetMapping("/getClozeList")
    public List<ClozeM> getClozeList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId){
        return testService.getClozeList(random, limitNum,questionBankId);
    }

    @GetMapping("/getNewList")
    public List<NewM> getNewList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int type){
        return testService.getNewList(random, limitNum,questionBankId,type);
    }

    @GetMapping("/getTestFtypeList")
    public List<TestFtype> getTestFtypeList(int questionBankId){
        return testService.getTestFtypeList(questionBankId);
    }

    @GetMapping("/getSpecialList")
    public List<TestFtype> getTestFtypeList(@RequestParam(defaultValue = "false") boolean random,
                                            @RequestParam(defaultValue = "0") int limitNum,
                                            int questionBankId,
                                            @RequestParam(defaultValue = "0")int num,
                                            int type){
        return testService.getTestFtypeList(questionBankId);
    }

    @GetMapping("/getTestCollectionList")
    public PageInfo<? extends QuestionM> getTestCollectionList(@RequestParam(defaultValue = "1") int pageNo,
                                                               @RequestParam(defaultValue = "8") int pageSize,
                                                               String account, int table){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestCollectionList(account,table));
    }

    @GetMapping("/getQuestionById")
    public QuestionM getQuestionById(int questionId, int table){
        return testService.getQuestionById(questionId,table);
    }

    @GetMapping("/getTestHistoryList")
    public PageInfo<? extends QuestionM> getTestHistoryList(@RequestParam(defaultValue = "1") int pageNo,
                                                               @RequestParam(defaultValue = "8") int pageSize,
                                                               String account, int table){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestHistoryList(account,table));
    }

    @GetMapping("/addTestRecord")
    public boolean addTestRecord(String account,int questionId,int table){
        return testService.addTestRecord(account, questionId,table);
    }

    @PostMapping("/saveAnswer")
    public boolean saveAnswer(@RequestBody QuestionAnswer questionAnswer){
        return testService.saveAnswer(questionAnswer);
    }

    @GetMapping("/getTestWrongList")
    public PageInfo<? extends QuestionM> getTestWrongList(@RequestParam(defaultValue = "1") int pageNo,
                                                            @RequestParam(defaultValue = "8") int pageSize,
                                                            String account, int table){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestWrongList(account,table));
    }
}
