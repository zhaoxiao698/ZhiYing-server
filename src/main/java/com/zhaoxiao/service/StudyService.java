package com.zhaoxiao.service;

import com.zhaoxiao.entity.study.ArticleNote;
import com.zhaoxiao.entity.study.Banner;
import com.zhaoxiao.mapper.StudyMapper;
import com.zhaoxiao.model.study.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    public ArticleDetailM getArticleDetail(String account, int articleId) {
        ArticleDetailM articleDetailM = studyMapper.getArticle(articleId);
        articleDetailM.setSentenceList(studyMapper.getSentenceList(articleId));
        //设置收藏
        articleDetailM.setCollect(studyMapper.getCollect(account, articleId) != null);
        return articleDetailM;
    }

    public boolean addArticleRecord(String account,int articleId) {
        if (studyMapper.getArticleRecord(account, articleId)==null){
            studyMapper.addArticleRecord(account,articleId);
            return true;
        } else {
            studyMapper.setArticleRecord(account,articleId,new Date());
            return true;
        }
    }
    public boolean collect(String account, int articleId, boolean collect) {
        if(collect){
            if(studyMapper.getCollect(account,articleId)==null) {
                return studyMapper.addCollect(account, articleId);
            } else {
                return true;
            }
        } else {
            if(studyMapper.getCollect(account,articleId)!=null) {
                return studyMapper.removeCollect(account, articleId);
            } else {
                return true;
            }
        }
    }

//    public boolean note(String account, int articleId, boolean note) {
//        if(note){
//            if(studyMapper.getNote(account,articleId)==null) {
//                return studyMapper.addCollect(account, articleId);
//            } else {
//                return true;
//            }
//        } else {
//            if(studyMapper.getCollect(account,articleId)!=null) {
//                return studyMapper.removeCollect(account, articleId);
//            } else {
//                return true;
//            }
//        }
//    }

    public boolean saveNote(String account, int articleId, String info) {
        if(studyMapper.getNote(account,articleId)==null) {
            return studyMapper.addNote(account, articleId, info);
        } else {
            return studyMapper.setNote(account, articleId, info);
        }
    }

    public boolean deleteNote(String account, int articleId) {
        if(studyMapper.getNote(account,articleId)!=null) {
            return studyMapper.removeNote(account, articleId);
        } else {
            return true;
        }
    }

    public ArticleNote getNote(String account, int articleId) {
        return studyMapper.getNote(account,articleId);
    }

    public List<ArticleM> getArticleCollectionList(String account) {
        return studyMapper.getArticleCollectionList(account);
    }

    public List<ChannelM> getChannelCollectionList(String account) {
        return studyMapper.getChannelCollectionList(account);
    }

    public List<ArticleM> getArticleHistoryList(String account) {
        return studyMapper.getArticleHistoryList(account);
    }

    public List<ArticleNoteDetail> getArticleNoteList(String account) {
        return studyMapper.getArticleNoteList(account);
    }
}
