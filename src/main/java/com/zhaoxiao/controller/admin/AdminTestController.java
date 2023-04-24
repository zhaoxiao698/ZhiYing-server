package com.zhaoxiao.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.test.BankedQuestion;
import com.zhaoxiao.entity.test.ListeningQuestion;
import com.zhaoxiao.model.test.*;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@ApiIgnore
@RestController
@BaseResponse
@RequestMapping("/admin1/test")
public class AdminTestController {
    @Autowired
    private AdminTestService adminTestService;

    @GetMapping("/getListeningList")
    public PageInfo<ListeningM> getListeningList(@RequestParam(defaultValue = "1") int pageNo,
                                                 @RequestParam(defaultValue = "8") int pageSize,
                                                 int questionBankId, int type){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getListeningList(questionBankId,type));
    }

    @GetMapping("/getBankedList")
    public PageInfo<BankedM> getBankedList(@RequestParam(defaultValue = "1") int pageNo,
                                       @RequestParam(defaultValue = "8") int pageSize,
                                       int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getBankedList(questionBankId));
    }

    @GetMapping("/getMatchList")
    public PageInfo<MatchM> getMatchList(@RequestParam(defaultValue = "1") int pageNo,
                                     @RequestParam(defaultValue = "8") int pageSize,
                                     int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getMatchList(questionBankId));
    }

    @GetMapping("/getCarefulList")
    public PageInfo<CarefulM> getCarefulList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getCarefulList(questionBankId));
    }

    @GetMapping("/getTranslationList")
    public PageInfo<TranslationM> getTranslationList(@RequestParam(defaultValue = "1") int pageNo,
                                                 @RequestParam(defaultValue = "8") int pageSize,
                                                 int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getTranslationList(questionBankId));
    }

    @GetMapping("/getWritingList")
    public PageInfo<WritingM> getWritingList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         int questionBankId, int type){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getWritingList(questionBankId,type));
    }

    @GetMapping("/getClozeList")
    public PageInfo<ClozeM> getClozeList(@RequestParam(defaultValue = "1") int pageNo,
                                     @RequestParam(defaultValue = "8") int pageSize,
                                     int questionBankId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getClozeList(questionBankId));
    }

    @GetMapping("/getNewList")
    public PageInfo<NewM> getNewList(@RequestParam(defaultValue = "1") int pageNo,
                                 @RequestParam(defaultValue = "8") int pageSize,
                                 int questionBankId, int type){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestService.getNewList(questionBankId,type));
    }

    @GetMapping("/getTestFtypeList")
    public List<TestFtype> getTestFtypeList(int questionBankId){
        return adminTestService.getTestFtypeList(questionBankId);
    }

    @PostMapping("/addListening")
    public boolean addListening(@RequestBody ListeningM listening){
        return adminTestService.addListening(listening);
    }

    @PostMapping("/addListeningQuestion")
    public boolean addListeningQuestion(@RequestBody ListeningQuestion listening){
        return adminTestService.addListeningQuestion(listening);
    }

    @PostMapping("/addBanked")
    public boolean addBanked(@RequestBody BankedM banked){
        return adminTestService.addBanked(banked);
    }

    @PostMapping("/addBankedQuestion")
    public boolean addBankedQuestion(@RequestBody BankedQuestion bankedQuestion){
        return adminTestService.addBankedQuestion(bankedQuestion);
    }
}
