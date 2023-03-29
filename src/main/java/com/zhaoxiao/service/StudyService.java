package com.zhaoxiao.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.Banner;
import com.zhaoxiao.mapper.StudyMapper;
import com.zhaoxiao.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudyService {
    @Autowired
    private StudyMapper studyMapper;

    public List<Banner> getBanner() {
        return studyMapper.getBanner();
    }

    public List<Recent> getRecentList() {
        return studyMapper.getRecentList();
    }

    public List<Hot> getHotList() {
        return studyMapper.getHotList();
    }

    public List<Ftype> getTypeList() {
        List<Ftype> ftypeList = studyMapper.getFtypeList();
        for (Ftype ftype : ftypeList) {
            ftype.setStypeList(studyMapper.getStypeList(ftype.getId()));
        }
        return ftypeList;
    }

    public Ftype getFtypeById(int ftypeId) {
        Ftype ftype = studyMapper.getFtypeById(ftypeId);
        ftype.setStypeList(studyMapper.getStypeList(ftypeId));
        return ftype;
    }

    public List<ChannelM> getChannelList(int ftypeId, int stypeId) {
        if(stypeId==0){
            return studyMapper.getChannelListAll(ftypeId);
        }
        return studyMapper.getChannelList(stypeId);
    }

    public ChannelM getChannelById(int channelId) {
        return studyMapper.getChannelById(channelId);
    }

    public List<ArticleM> getArticleList(int channelId, boolean sort, boolean order) {
        //sort和order为boolean时
        if(sort){
            if(order){
                return studyMapper.getArticleList(channelId,"title","asc");
            }
            return studyMapper.getArticleList(channelId,"title","desc");
        }
        if(order){
            return studyMapper.getArticleList(channelId,"addTime","asc");
        }
        return studyMapper.getArticleList(channelId,"addTime","desc");

        //sort和order为String时
//        if((sort.equals("name")||sort.equals("addTime"))&&(order.equals("asc")||order.equals("desc"))){
//            return studyMapper.getArticleList(channelId,sort,order);
//        }
//        return studyMapper.getArticleList(channelId,"title","asc");

        //sort和order为int时
//        switch (sort){
//            case 1:
//                switch (order){
//                    case 1: return studyMapper.getArticleList(channelId,"addTime","desc");
//                    default: return studyMapper.getArticleList(channelId,"addTime","asc");
//                }
//            default:
//                switch (order){
//                    case 1: return studyMapper.getArticleList(channelId,"title","desc");
//                    default: return studyMapper.getArticleList(channelId,"title","asc");
//            }
//        }
    }

    public ArticleDetailM getArticleDetail(int articleId) {
        ArticleDetailM articleDetailM = studyMapper.getArticle(articleId);
        articleDetailM.setSentenceList(studyMapper.getSentenceList(articleId));
        return articleDetailM;
    }
}
