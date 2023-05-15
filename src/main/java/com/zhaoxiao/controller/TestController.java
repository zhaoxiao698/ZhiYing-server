package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.model.test.TruePaper;
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
                                         int questionBankId, int num,int type, int source,String account){
        return testService.getListeningList(random,limitNum,questionBankId,num,type,source,account);
    }

    @GetMapping("/getBankedList")
    public List<BankedM> getBankedList(@RequestParam(defaultValue = "false") boolean random,
                                       @RequestParam(defaultValue = "0") int limitNum,
                                       int questionBankId, int source,String account){
        return testService.getBankedList(random, limitNum,questionBankId,source,account);
    }

    @GetMapping("/getMatchList")
    public List<MatchM> getMatchList(@RequestParam(defaultValue = "false") boolean random,
                                      @RequestParam(defaultValue = "0") int limitNum,
                                      int questionBankId, int source,String account){
        return testService.getMatchList(random, limitNum,questionBankId,source,account);
    }

    @GetMapping("/getCarefulList")
    public List<CarefulM> getCarefulList(@RequestParam(defaultValue = "false") boolean random,
                                       @RequestParam(defaultValue = "0") int limitNum,
                                       int questionBankId, int source,String account){
        return testService.getCarefulList(random, limitNum,questionBankId,source,account);
    }

    @GetMapping("/getTranslationList")
    public List<TranslationM> getTranslationList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int source,String account){
        return testService.getTranslationList(random, limitNum,questionBankId,source,account);
    }

    @GetMapping("/getWritingList")
    public List<WritingM> getWritingList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int type, int source,String account){
        return testService.getWritingList(random, limitNum,questionBankId,type,source,account);
    }

    @GetMapping("/getClozeList")
    public List<ClozeM> getClozeList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int source,String account){
        return testService.getClozeList(random, limitNum,questionBankId,source,account);
    }

    @GetMapping("/getNewList")
    public List<NewM> getNewList(@RequestParam(defaultValue = "false") boolean random,
                                                 @RequestParam(defaultValue = "0") int limitNum,
                                                 int questionBankId, int type, int source,String account){
        return testService.getNewList(random, limitNum,questionBankId,type,source,account);
    }

    @GetMapping("/getTestFtypeList")
    public List<TestFtype> getTestFtypeList(String account, int questionBankId){
        return testService.getTestFtypeList(account,questionBankId);
    }

//    @GetMapping("/getSpecialList")
//    public List<TestFtype> getTestFtypeList(@RequestParam(defaultValue = "false") boolean random,
//                                            @RequestParam(defaultValue = "0") int limitNum,
//                                            int questionBankId,
//                                            @RequestParam(defaultValue = "0")int num,
//                                            int type){
//        return testService.getTestFtypeList(questionBankId);
//    }

    @GetMapping("/getTestCollectionList")
    public PageInfo<? extends QuestionM> getTestCollectionList(@RequestParam(defaultValue = "1") int pageNo,
                                                               @RequestParam(defaultValue = "8") int pageSize,
                                                               String account, int table){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestCollectionList(account,table));
    }

    @GetMapping("/getQuestionById")
    public QuestionM getQuestionById(int questionId, int table,String account){
        return testService.getQuestionById(questionId,table,account);
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

    @GetMapping("/getTruePaperList")
    public PageInfo<TruePaper> getTruePaperList(@RequestParam(defaultValue = "1") int pageNo,
                                                @RequestParam(defaultValue = "8") int pageSize,
                                                int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestTruePaperList(questionBankId));
    }

    @GetMapping("/getTruePaper")
    public Paper getTruePaper(int truePaperId,String account){
        return testService.getTruePaper(truePaperId,account);
    }

    @GetMapping("/getTestNoteList")
    public PageInfo<TestNoteDetail> getTestNoteList(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "8") int pageSize,
                                 String account, int table){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(testService.getTestNoteList(account,table));
    }

    @GetMapping("/getTestNote")
    public TestNoteDetail getTestNote(String account, int questionId, int table){
        return testService.getTestNote(account,questionId,table);
    }

    @GetMapping("/saveTestNote")
    public boolean saveTestNote(String account,int questionId, int table,String info){
        return testService.saveTestNote(account,questionId,table,info);
    }

    @GetMapping("/deleteTestNote")
    public boolean deleteTestNote(String account,int questionId, int table){
        return testService.deleteTestNote(account,questionId,table);
    }

    @GetMapping("/getExam")
    public Paper getExam(int questionBankId,String account){
        return testService.getExam(questionBankId,account);
    }

    @GetMapping("/collect")
    public boolean collect(String account,int questionId,int table,boolean collect){
        return testService.collect(account,questionId,table,collect);
    }
}
