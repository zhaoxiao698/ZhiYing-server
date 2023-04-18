package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.study.ArticleNote;
import com.zhaoxiao.entity.study.Banner;
import com.zhaoxiao.model.study.*;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/study")
public class StudyController {
    @Autowired
    private StudyService studyService;

    @GetMapping("/getBanner")
    public List<Banner> getBanner(){
        return studyService.getBanner();
    }

    @GetMapping("/getRecentList")
    public PageInfo<Recent> getRecentList(@RequestParam(defaultValue = "1") int pageNo,
                                      @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(studyService.getRecentList());
    }

    @GetMapping("/getHotList")
    public PageInfo<Hot> getHotList(@RequestParam(defaultValue = "1") int pageNo,
                                    @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(studyService.getHotList());
    }

    @GetMapping("/getFtypeList")
    public List<Ftype> getFtypeList(){
        return studyService.getTypeList();
    }

    @GetMapping("/getFtypeById")
    public Ftype getFtypeById(int ftypeId){
        return studyService.getFtypeById(ftypeId);
    }

    @GetMapping("/getChannelList")
    public PageInfo<ChannelM> getChannelList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             int ftypeId, int stypeId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(studyService.getChannelList(ftypeId,stypeId));
    }

    @GetMapping("/getChannelById")
    public ChannelM getChannelById(int channelId){
        return studyService.getChannelById(channelId);
    }

    @GetMapping("/getArticleList")
    public PageInfo<ArticleM> getArticleList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             int channelId,
                                             @RequestParam(name = "title",defaultValue = "false") boolean sort,
                                             @RequestParam(name = "asc",defaultValue = "false") boolean order){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(studyService.getArticleList(channelId,sort,order));
    }

    @GetMapping("/getArticleDetail")
    public ArticleDetailM getArticleDetail(String account, int articleId){
        return studyService.getArticleDetail(account,articleId);
    }

    @GetMapping("/addArticleRecord")
    public boolean addArticleRecord(String account,int articleId){
        return studyService.addArticleRecord(account, articleId);
    }

    @GetMapping("/collect")
    public boolean collect(String account,int articleId,boolean collect){
        return studyService.collect(account,articleId,collect);
    }

    @GetMapping("/saveNote")
    public boolean saveNote(String account,int articleId,String info){
        return studyService.saveNote(account,articleId,info);
    }

    @GetMapping("/deleteNote")
    public boolean deleteNote(String account,int articleId){
        return studyService.deleteNote(account,articleId);
    }

    @GetMapping("/getNote")
    public ArticleNote getNote(String account, int articleId){
        return studyService.getNote(account,articleId);
    }
}
