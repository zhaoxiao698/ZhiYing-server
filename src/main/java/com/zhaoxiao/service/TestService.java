package com.zhaoxiao.service;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.mapper.TestMapper;
import com.zhaoxiao.model.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public List<ListeningM> getListeningList(boolean random, int limitNum, int questionBankId,int num,int type, int source,String account) {
        List<ListeningM> listeningList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        String numS = "";
        if(num>0) numS = "and num=" + num;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        switch (source) {
            case 0:
                listeningList = testMapper.getListeningList0(randomS, limitNumS, questionBankId, numS, typeS,account);
                break;
            case 1:
                listeningList = testMapper.getListeningList1(randomS, limitNumS, questionBankId, numS, typeS,account);
                break;
            case 2:
                listeningList = testMapper.getListeningList2(randomS, limitNumS, questionBankId, numS, typeS,account);
                break;
            default:
                listeningList = testMapper.getListeningList(randomS, limitNumS, questionBankId, numS, typeS);
                break;
        }

//        if (random) {
//            if (limitNum > 0) listeningList = testMapper.getListeningList("order by rand()", "limit " + limitNum, questionBankId, num);
//            else listeningList = testMapper.getListeningList("order by rand()", "", questionBankId, num);
//        } else {
//            if (limitNum > 0) listeningList = testMapper.getListeningList("", "limit " + limitNum, questionBankId, num);
//            else listeningList = testMapper.getListeningList("", "", questionBankId, num);
//        }

        for (ListeningM listening : listeningList) {
            List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
            for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                listeningQuestion.setOrder(i++);
            }
            listening.setListeningQuestionList(listeningQuestionList);
            listening.setSubQuestionNum(listeningQuestionList.size());
            listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),1) != null);
        }
        return listeningList;
    }

    public List<BankedM> getBankedList(boolean random, int limitNum, int questionBankId, int source,String account) {
        List<BankedM> bankedList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        switch (source) {
            case 0:
                bankedList = testMapper.getBankedList0(randomS, limitNumS, questionBankId,account);
                break;
            case 1:
                bankedList = testMapper.getBankedList1(randomS, limitNumS, questionBankId,account);
                break;
            case 2:
                bankedList = testMapper.getBankedList2(randomS, limitNumS, questionBankId,account);
                break;
            default:
                bankedList = testMapper.getBankedList(randomS, limitNumS, questionBankId);
                break;
        }

//        if (random) {
//            if (limitNum > 0) bankedList = testMapper.getBankedList("order by rand()", "limit " + limitNum, questionBankId);
//            else bankedList = testMapper.getBankedList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) bankedList = testMapper.getBankedList("", "limit " + limitNum, questionBankId);
//            else bankedList = testMapper.getBankedList("", "", questionBankId);
//        }

        for (BankedM banked : bankedList) {
            List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
            for (BankedQuestion bankedQuestion : bankedQuestionList) {
                bankedQuestion.setOrder(i++);
            }
            banked.setWordList(banked.getWord().split(","));
            banked.setBankedQuestionList(bankedQuestionList);
            banked.setSubQuestionNum(bankedQuestionList.size());
            banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),2) != null);
        }
        return bankedList;
    }

    public List<MatchM> getMatchList(boolean random, int limitNum, int questionBankId, int source,String account) {
        List<MatchM> matchList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        switch (source){
            case 0:
                matchList = testMapper.getMatchList0(randomS, limitNumS, questionBankId,account);
                break;
            case 1:
                matchList = testMapper.getMatchList1(randomS, limitNumS, questionBankId,account);
                break;
            case 2:
                matchList = testMapper.getMatchList2(randomS, limitNumS, questionBankId,account);
                break;
            default:
                matchList = testMapper.getMatchList(randomS, limitNumS, questionBankId);
                break;
        }

//        if (random) {
//            if (limitNum > 0) matchList = testMapper.getMatchList("order by rand()", "limit " + limitNum, questionBankId);
//            else matchList = testMapper.getMatchList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) matchList = testMapper.getMatchList("", "limit " + limitNum, questionBankId);
//            else matchList = testMapper.getMatchList("", "", questionBankId);
//        }

        for (MatchM match : matchList) {
            List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
            for (MatchQuestion matchQuestion : matchQuestionList) {
                matchQuestion.setOrder(i++);
            }
            match.setMatchQuestionList(matchQuestionList);
            match.setSubQuestionNum(matchQuestionList.size());
            match.setCollectStatus(testMapper.getCollect(account, match.getId(),3) != null);
        }
        return matchList;
    }

    public List<CarefulM> getCarefulList(boolean random, int limitNum, int questionBankId, int source,String account) {
        List<CarefulM> carefulList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        switch (source){
            case 0:
                carefulList = testMapper.getCarefulList0(randomS, limitNumS, questionBankId,account);
                break;
            case 1:
                carefulList = testMapper.getCarefulList1(randomS, limitNumS, questionBankId,account);
                break;
            case 2:
                carefulList = testMapper.getCarefulList2(randomS, limitNumS, questionBankId,account);
                break;
            default:
                carefulList = testMapper.getCarefulList(randomS, limitNumS, questionBankId);
                break;
        }

//        if (random) {
//            if (limitNum > 0) carefulList = testMapper.getCarefulList("order by rand()", "limit " + limitNum, questionBankId);
//            else carefulList = testMapper.getCarefulList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) carefulList = testMapper.getCarefulList("", "limit " + limitNum, questionBankId);
//            else carefulList = testMapper.getCarefulList("", "", questionBankId);
//        }

        for (CarefulM careful : carefulList) {
            List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
            for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                carefulQuestion.setOrder(i++);
            }
            careful.setCarefulQuestionList(carefulQuestionList);
            careful.setSubQuestionNum(carefulQuestionList.size());
            careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),4) != null);
        }
        return carefulList;
    }

    public List<TranslationM> getTranslationList(boolean random, int limitNum, int questionBankId, int source,String account) {
        List<TranslationM> translationList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        switch (source){
            case 0:
                translationList = testMapper.getTranslationList0(randomS, limitNumS, questionBankId,account);
                break;
            case 1:
                translationList = testMapper.getTranslationList1(randomS, limitNumS, questionBankId,account);
                break;
            case 2:
                translationList = testMapper.getTranslationList2(randomS, limitNumS, questionBankId,account);
                break;
            default:
                translationList = testMapper.getTranslationList(randomS, limitNumS, questionBankId);
                break;
        }

//        if (random) {
//            if (limitNum > 0) translationList = testMapper.getTranslationList("order by rand()", "limit " + limitNum, questionBankId);
//            else translationList = testMapper.getTranslationList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) translationList = testMapper.getTranslationList("", "limit " + limitNum, questionBankId);
//            else translationList = testMapper.getTranslationList("", "", questionBankId);
//        }

        for (TranslationM translation : translationList) {
            translation.setOrder(i++);
            translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),5) != null);
        }
        return translationList;
    }

    public List<WritingM> getWritingList(boolean random, int limitNum, int questionBankId, int type, int source,String account) {
        List<WritingM> writingList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        switch (source){
            case 0:
                writingList = testMapper.getWritingList0(randomS, limitNumS, questionBankId, typeS,account);
                break;
            case 1:
                writingList = testMapper.getWritingList1(randomS, limitNumS, questionBankId, typeS,account);
                break;
            case 2:
                writingList = testMapper.getWritingList2(randomS, limitNumS, questionBankId, typeS,account);
                break;
            default:
                writingList = testMapper.getWritingList(randomS, limitNumS, questionBankId, typeS);
                break;
        }

//        if (random) {
//            if (limitNum > 0) writingList = testMapper.getWritingList("order by rand()", "limit " + limitNum, questionBankId);
//            else writingList = testMapper.getWritingList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) writingList = testMapper.getWritingList("", "limit " + limitNum, questionBankId);
//            else writingList = testMapper.getWritingList("", "", questionBankId);
//        }

        for (WritingM writing : writingList) {
            writing.setOrder(i++);
            writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),6) != null);
        }
        return writingList;
    }

    public List<ClozeM> getClozeList(boolean random, int limitNum, int questionBankId, int source,String account) {
        List<ClozeM> clozeList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        switch (source){
            case 0:
                clozeList = testMapper.getClozeList0(randomS, limitNumS, questionBankId,account);
                break;
            case 1:
                clozeList = testMapper.getClozeList1(randomS, limitNumS, questionBankId,account);
                break;
            case 2:
                clozeList = testMapper.getClozeList2(randomS, limitNumS, questionBankId,account);
                break;
            default:
                clozeList = testMapper.getClozeList(randomS, limitNumS, questionBankId);
                break;
        }

//        if (random) {
//            if (limitNum > 0) clozeList = testMapper.getClozeList("order by rand()", "limit " + limitNum, questionBankId);
//            else clozeList = testMapper.getClozeList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) clozeList = testMapper.getClozeList("", "limit " + limitNum, questionBankId);
//            else clozeList = testMapper.getClozeList("", "", questionBankId);
//        }

        for (ClozeM cloze : clozeList) {
            List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
            for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                clozeQuestion.setOrder(i++);
            }
            cloze.setClozeQuestionList(clozeQuestionList);
            cloze.setSubQuestionNum(clozeQuestionList.size());
            cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),7) != null);
        }
        return clozeList;
    }

    public List<NewM> getNewList(boolean random, int limitNum, int questionBankId, int type, int source,String account) {
        List<NewM> newList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        switch (source){
            case 0:
                newList = testMapper.getNewList0(randomS, limitNumS, questionBankId, typeS,account);
                break;
            case 1:
                newList = testMapper.getNewList1(randomS, limitNumS, questionBankId, typeS,account);
                break;
            case 2:
                newList = testMapper.getNewList2(randomS, limitNumS, questionBankId, typeS,account);
                break;
            default:
                newList = testMapper.getNewList(randomS, limitNumS, questionBankId, typeS);
                break;
        }

//        if (random) {
//            if (limitNum > 0) newList = testMapper.getNewList("order by rand()", "limit " + limitNum, questionBankId);
//            else newList = testMapper.getNewList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) newList = testMapper.getNewList("", "limit " + limitNum, questionBankId);
//            else newList = testMapper.getNewList("", "", questionBankId);
//        }

        for (NewM newM : newList) {
            List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
            for (NewQuestion newQuestion : newQuestionList) {
                newQuestion.setOrder(i++);
            }
            newM.setNewQuestionList(newQuestionList);
            newM.setSubQuestionNum(newQuestionList.size());
            newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),8) != null);
        }
        return newList;
    }

    public List<TestFtype> getTestFtypeList(String account,int questionBankId) {
        List<TestFtype> ftypes = new ArrayList<>();
        TestFtype ftype1,ftype2,ftype3,ftype4,ftype5;
        List<TestStype> stypes1,stypes2,stypes3,stypes4,stypes5;
        int num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14;
        int finish1,finish2,finish3,finish4,finish5,finish6,finish7,finish8,finish9,finish10,finish11,finish12,finish13,finish14;
        int right1,right2,right3,right4,right5,right6,right7,right8,right9,right10,right11,right12,right13,right14;

        switch (questionBankId) {
            case 1:
                stypes1 = new ArrayList<>();

                num1 = testMapper.getListeningCount(1, questionBankId);
                finish1 = testMapper.getListeningFinish(account,1, questionBankId);
                right1 = testMapper.getListeningRight(account,1, questionBankId);
                stypes1.add(new TestStype(1, "短篇新闻", num1, finish1, right1));

                num2 = testMapper.getListeningCount(2, questionBankId);
                finish2 = testMapper.getListeningFinish(account,2, questionBankId);
                right2 = testMapper.getListeningRight(account,2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, finish2, right2));

                num3 = testMapper.getListeningCount(3, questionBankId);
                finish3 = testMapper.getListeningFinish(account,3, questionBankId);
                right3 = testMapper.getListeningRight(account,3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, finish3, right3));

                ftype1 = new TestFtype(1, "听力", stypes1, false, num1+num2+num3, finish1+finish2+finish3, right1+right2+right3);


                stypes2 = new ArrayList<>();

                num5 = testMapper.getBankedCount(questionBankId);
                finish5 = testMapper.getBankedFinish(account,questionBankId);
                right5 = testMapper.getBankedRight(account,questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, finish5, right5));

                num6 = testMapper.getMatchCount(questionBankId);
                finish6 = testMapper.getMatchFinish(account,questionBankId);
                right6 = testMapper.getMatchRight(account,questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, finish6, right6));

                num7 = testMapper.getCarefulCount(questionBankId);
                finish7 = testMapper.getCarefulFinish(account,questionBankId);
                right7 = testMapper.getCarefulRight(account,questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, finish7, right7));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, finish5+finish6+finish7, right5+right6+right7);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                finish8 = testMapper.getTranslationFinish(account,questionBankId);
                right8 = testMapper.getTranslationRight(account,questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, finish8, right8));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, finish8, right8);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(13,questionBankId);
                finish9 = testMapper.getWritingFinish(account,13, questionBankId);
                right9 = testMapper.getWritingRight(account,13, questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, finish9, right9));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, finish9, right9);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 2:
                stypes1 = new ArrayList<>();
                num2 = testMapper.getListeningCount(2, questionBankId);
                finish2 = testMapper.getListeningFinish(account,2, questionBankId);
                right2 = testMapper.getListeningRight(account,2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, finish2, right2));

                num3 = testMapper.getListeningCount(3, questionBankId);
                finish3 = testMapper.getListeningFinish(account,3, questionBankId);
                right3 = testMapper.getListeningRight(account,3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, finish3, right3));

                num4 = testMapper.getListeningCount(4, questionBankId);
                finish4 = testMapper.getListeningFinish(account,4, questionBankId);
                right4 = testMapper.getListeningRight(account,4, questionBankId);
                stypes1.add(new TestStype(4, "讲话/报道/讲座", num4, finish4, right4));


                ftype1 = new TestFtype(1, "听力", stypes1, false, num2+num3+num4, finish2+finish3+finish4, right2+right3+right4);


                stypes2 = new ArrayList<>();

                num5 = testMapper.getBankedCount(questionBankId);
                finish5 = testMapper.getBankedFinish(account,questionBankId);
                right5 = testMapper.getBankedRight(account,questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, finish5, right5));

                num6 = testMapper.getMatchCount(questionBankId);
                finish6 = testMapper.getMatchFinish(account,questionBankId);
                right6 = testMapper.getMatchRight(account,questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, finish6, right6));

                num7 = testMapper.getCarefulCount(questionBankId);
                finish7 = testMapper.getCarefulFinish(account,questionBankId);
                right7 = testMapper.getCarefulRight(account,questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, finish7, right7));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, finish5+finish6+finish7, right5+right6+right7);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                finish8 = testMapper.getTranslationFinish(account,questionBankId);
                right8 = testMapper.getTranslationRight(account,questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, finish8, right8));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, finish8, right8);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(13,questionBankId);
                finish9 = testMapper.getWritingFinish(account,13, questionBankId);
                right9 = testMapper.getWritingRight(account,13, questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, finish9, right9));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, finish9, right9);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 3:
            case 4:
                stypes5 = new ArrayList<>();

                num10 = testMapper.getClozeCount(questionBankId);
                finish10 = testMapper.getClozeFinish(account,questionBankId);
                right10 = testMapper.getClozeRight(account,questionBankId);
                stypes5.add(new TestStype(15,"完形填空",num10,finish10,right10));

                ftype5 = new TestFtype(5, "完形填空", stypes5, false, num10, finish10, right10);


                stypes2 = new ArrayList<>();

                num7 = testMapper.getCarefulCount(questionBankId);
                finish7 = testMapper.getCarefulFinish(account,questionBankId);
                right7 = testMapper.getCarefulRight(account,questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, finish7, right7));

                num11 = testMapper.getNewCount(8,questionBankId);
                finish11 = testMapper.getNewFinish(account,8,questionBankId);
                right11 = testMapper.getNewRight(account,8,questionBankId);
                stypes2.add(new TestStype(8, "新题型-七选五", num11, finish11, right11));

                num12 = testMapper.getNewCount(9,questionBankId);
                finish12 = testMapper.getNewFinish(account,9, questionBankId);
                right12 = testMapper.getNewRight(account,9, questionBankId);
                stypes2.add(new TestStype(9, "新题型-排序", num12, finish12, right12));

                num13 = testMapper.getNewCount(10,questionBankId);
                finish13 = testMapper.getNewFinish(account,10, questionBankId);
                right13 = testMapper.getNewRight(account,10, questionBankId);
                stypes2.add(new TestStype(10, "新题型-小标题", num13, finish13, right13));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num7+num11+num12+num13, finish7+finish11+finish12+finish13, right7+right11+right12+right13);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                finish8 = testMapper.getTranslationFinish(account,questionBankId);
                right8 = testMapper.getTranslationRight(account,questionBankId);
                stypes3.add(new TestStype(12, "英译汉", num8, finish8, right8));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, finish8, right8);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(14,questionBankId);
                finish9 = testMapper.getWritingFinish(account,14, questionBankId);
                right9 = testMapper.getWritingRight(account,14, questionBankId);
                stypes4.add(new TestStype(14, "应用文", num9, finish9, right9));

                num14 = testMapper.getWritingCount(13,questionBankId);
                finish14 = testMapper.getWritingFinish(account,13, questionBankId);
                right14 = testMapper.getWritingRight(account,13, questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, finish14, right14));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9+num14, finish9+finish14, right9+right14);


                ftypes.add(ftype5);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;


            default:break;
        }
//        ftypes.add(new TestFtype(""));
        return ftypes;
    }

    public List<? extends QuestionM> getTestCollectionList(String account, int table) {
        int i = 1;
        switch (table){
            case 1:
                List<ListeningM> listeningList = testMapper.getListeningCollectionList(account);
                for (ListeningM listening : listeningList) {
                    List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
                    for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                        listeningQuestion.setOrder(i++);
                    }
                    listening.setListeningQuestionList(listeningQuestionList);
                    listening.setSubQuestionNum(listeningQuestionList.size());
                    listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),table) != null);
                }
                return listeningList;
            case 2:
                List<BankedM> bankedList = testMapper.getBankedCollectionList(account);
                for (BankedM banked : bankedList) {
                    List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
                    for (BankedQuestion bankedQuestion : bankedQuestionList) {
                        bankedQuestion.setOrder(i++);
                    }
                    banked.setWordList(banked.getWord().split(","));
                    banked.setBankedQuestionList(bankedQuestionList);
                    banked.setSubQuestionNum(bankedQuestionList.size());
                    banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),table) != null);
                }
                return bankedList;
            case 3:
                List<MatchM> matchList = testMapper.getMatchCollectionList(account);
                for (MatchM match : matchList) {
                    List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
                    for (MatchQuestion matchQuestion : matchQuestionList) {
                        matchQuestion.setOrder(i++);
                    }
                    match.setMatchQuestionList(matchQuestionList);
                    match.setSubQuestionNum(matchQuestionList.size());
                    match.setCollectStatus(testMapper.getCollect(account, match.getId(),table) != null);
                }
                return matchList;
            case 4:
                List<CarefulM> carefulList = testMapper.getCarefulCollectionList(account);
                for (CarefulM careful : carefulList) {
                    List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
                    for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                        carefulQuestion.setOrder(i++);
                    }
                    careful.setCarefulQuestionList(carefulQuestionList);
                    careful.setSubQuestionNum(carefulQuestionList.size());
                    careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),table) != null);
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationCollectionList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                    translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),table) != null);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingCollectionList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
                    writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),table) != null);
                }
                return writingList;
            case 7:
                List<ClozeM> clozeList = testMapper.getClozeCollectionList(account);
                for (ClozeM cloze : clozeList) {
                    List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
                    for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                        clozeQuestion.setOrder(i++);
                    }
                    cloze.setClozeQuestionList(clozeQuestionList);
                    cloze.setSubQuestionNum(clozeQuestionList.size());
                    cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),table) != null);
                }
                return clozeList;
            case 8:
                List<NewM> newList = testMapper.getNewCollectionList(account);
                for (NewM newM : newList) {
                    List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
                    for (NewQuestion newQuestion : newQuestionList) {
                        newQuestion.setOrder(i++);
                    }
                    newM.setNewQuestionList(newQuestionList);
                    newM.setSubQuestionNum(newQuestionList.size());
                    newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),table) != null);
                }
                return newList;
        }
        return null;
    }

    public QuestionM getQuestionById(int questionId, int table,String account) {
        int i = 1;
        switch (table){
            case 1:
                ListeningM listening = testMapper.getListeningQuestionById(questionId);
                List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
                for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                    listeningQuestion.setOrder(i++);
                }
                listening.setListeningQuestionList(listeningQuestionList);
                listening.setSubQuestionNum(listeningQuestionList.size());
                listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),table) != null);
                return listening;
            case 2:
                BankedM banked = testMapper.getBankedQuestionById(questionId);
                List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
                for (BankedQuestion bankedQuestion : bankedQuestionList) {
                    bankedQuestion.setOrder(i++);
                }
                banked.setWordList(banked.getWord().split(","));
                banked.setBankedQuestionList(bankedQuestionList);
                banked.setSubQuestionNum(bankedQuestionList.size());
                banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),table) != null);
                return banked;
            case 3:
                MatchM match = testMapper.getMatchQuestionById(questionId);
                List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
                for (MatchQuestion matchQuestion : matchQuestionList) {
                    matchQuestion.setOrder(i++);
                }
                match.setMatchQuestionList(matchQuestionList);
                match.setSubQuestionNum(matchQuestionList.size());
                match.setCollectStatus(testMapper.getCollect(account, match.getId(),table) != null);
                return match;
            case 4:
                CarefulM careful = testMapper.getCarefulQuestionById(questionId);
                List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
                for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                    carefulQuestion.setOrder(i++);
                }
                careful.setCarefulQuestionList(carefulQuestionList);
                careful.setSubQuestionNum(carefulQuestionList.size());
                careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),table) != null);
                return careful;
            case 5:
                TranslationM translation = testMapper.getTranslationQuestionById(questionId);
                translation.setOrder(i++);
                translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),table) != null);
                return translation;
            case 6:
                WritingM writing = testMapper.getWritingQuestionById(questionId);
                writing.setOrder(i++);
                writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),table) != null);
                return writing;
            case 7:
                ClozeM cloze = testMapper.getClozeQuestionById(questionId);
                List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
                for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                    clozeQuestion.setOrder(i++);
                }
                cloze.setClozeQuestionList(clozeQuestionList);
                cloze.setSubQuestionNum(clozeQuestionList.size());
                cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),table) != null);
                return cloze;
            case 8:
                NewM newM = testMapper.getNewQuestionById(questionId);
                List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
                for (NewQuestion newQuestion : newQuestionList) {
                    newQuestion.setOrder(i++);
                }
                newM.setNewQuestionList(newQuestionList);
                newM.setSubQuestionNum(newQuestionList.size());
                newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),table) != null);
                return newM;
        }
        return null;
    }

    public List<? extends QuestionM> getTestHistoryList(String account, int table) {
        int i = 1;
        switch (table){
            case 1:
                List<ListeningM> listeningList = testMapper.getListeningHistoryList(account);
                for (ListeningM listening : listeningList) {
                    List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
                    for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                        listeningQuestion.setOrder(i++);
                    }
                    listening.setListeningQuestionList(listeningQuestionList);
                    listening.setSubQuestionNum(listeningQuestionList.size());
                    listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),table) != null);
                }
                return listeningList;
            case 2:
                List<BankedM> bankedList = testMapper.getBankedHistoryList(account);
                for (BankedM banked : bankedList) {
                    List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
                    for (BankedQuestion bankedQuestion : bankedQuestionList) {
                        bankedQuestion.setOrder(i++);
                    }
                    banked.setWordList(banked.getWord().split(","));
                    banked.setBankedQuestionList(bankedQuestionList);
                    banked.setSubQuestionNum(bankedQuestionList.size());
                    banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),table) != null);
                }
                return bankedList;
            case 3:
                List<MatchM> matchList = testMapper.getMatchHistoryList(account);
                for (MatchM match : matchList) {
                    List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
                    for (MatchQuestion matchQuestion : matchQuestionList) {
                        matchQuestion.setOrder(i++);
                    }
                    match.setMatchQuestionList(matchQuestionList);
                    match.setSubQuestionNum(matchQuestionList.size());
                    match.setCollectStatus(testMapper.getCollect(account, match.getId(),table) != null);
                }
                return matchList;
            case 4:
                List<CarefulM> carefulList = testMapper.getCarefulHistoryList(account);
                for (CarefulM careful : carefulList) {
                    List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
                    for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                        carefulQuestion.setOrder(i++);
                    }
                    careful.setCarefulQuestionList(carefulQuestionList);
                    careful.setSubQuestionNum(carefulQuestionList.size());
                    careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),table) != null);
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationHistoryList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                    translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),table) != null);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingHistoryList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
                    writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),table) != null);
                }
                return writingList;
            case 7:
                List<ClozeM> clozeList = testMapper.getClozeHistoryList(account);
                for (ClozeM cloze : clozeList) {
                    List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
                    for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                        clozeQuestion.setOrder(i++);
                    }
                    cloze.setClozeQuestionList(clozeQuestionList);
                    cloze.setSubQuestionNum(clozeQuestionList.size());
                    cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),table) != null);
                }
                return clozeList;
            case 8:
                List<NewM> newList = testMapper.getNewHistoryList(account);
                for (NewM newM : newList) {
                    List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
                    for (NewQuestion newQuestion : newQuestionList) {
                        newQuestion.setOrder(i++);
                    }
                    newM.setNewQuestionList(newQuestionList);
                    newM.setSubQuestionNum(newQuestionList.size());
                    newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),table) != null);
                }
                return newList;
        }
        return null;
    }

    public boolean addTestRecord(String account,int questionId,int table) {
        if (testMapper.getTestRecord(account, questionId,table)==null){
//            testMapper.addTestRecord(account,questionId,table);
            return true;
        } else {
            testMapper.setTestRecord(account,questionId,table,new Date());
            return true;
        }
    }

    public boolean saveAnswer(QuestionAnswer questionAnswer) {
        if (testMapper.getTestRecord(questionAnswer.getAccount(), questionAnswer.getQuestionId(),questionAnswer.getTable())==null){
            addAnswer(questionAnswer);
            return true;
        } else {
            setAnswer(questionAnswer);
            return true;
        }
    }

    private void addAnswer(QuestionAnswer questionAnswer) {
        testMapper.addAnswer(questionAnswer.getAccount(), questionAnswer.getQuestionId(),
                questionAnswer.getTable(), questionAnswer.getAnswer(), questionAnswer.getRight());
        addSubAnswer(questionAnswer);
    }

    private void addSubAnswer(QuestionAnswer questionAnswer) {
        if (questionAnswer.getTable() != 5 && questionAnswer.getTable() != 6) {
            for (SubQuestionAnswer subQuestionAnswer : questionAnswer.getSubQuestionAnswerList()) {
                if (testMapper.getSubTestRecord(questionAnswer.getAccount(), subQuestionAnswer.getSubQuestionId(), questionAnswer.getTable())==null){
                    testMapper.addSubAnswer(questionAnswer.getAccount(),subQuestionAnswer.getSubQuestionId(),
                            questionAnswer.getTable(),subQuestionAnswer.getAnswer(), subQuestionAnswer.getRight());
                } else {
                    testMapper.setSubAnswer(questionAnswer.getAccount(),subQuestionAnswer.getSubQuestionId(),
                            questionAnswer.getTable(),subQuestionAnswer.getAnswer(), subQuestionAnswer.getRight(), new Date());
                }
            }
        }
    }

    private void setAnswer(QuestionAnswer questionAnswer) {
        testMapper.setAnswer(questionAnswer.getAccount(), questionAnswer.getQuestionId(),
                questionAnswer.getTable(), questionAnswer.getAnswer(), questionAnswer.getRight(), new Date());
        addSubAnswer(questionAnswer);
    }

    public List<? extends QuestionM> getTestWrongList(String account, int table) {
        int i = 1;
        switch (table){
            case 1:
                List<ListeningM> listeningList = testMapper.getListeningWrongList(account);
                for (ListeningM listening : listeningList) {
                    List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
                    for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                        listeningQuestion.setOrder(i++);
                    }
                    listening.setListeningQuestionList(listeningQuestionList);
                    listening.setSubQuestionNum(listeningQuestionList.size());
                    listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),table) != null);
                }
                return listeningList;
            case 2:
                List<BankedM> bankedList = testMapper.getBankedWrongList(account);
                for (BankedM banked : bankedList) {
                    List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
                    for (BankedQuestion bankedQuestion : bankedQuestionList) {
                        bankedQuestion.setOrder(i++);
                    }
                    banked.setWordList(banked.getWord().split(","));
                    banked.setBankedQuestionList(bankedQuestionList);
                    banked.setSubQuestionNum(bankedQuestionList.size());
                    banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),table) != null);
                }
                return bankedList;
            case 3:
                List<MatchM> matchList = testMapper.getMatchWrongList(account);
                for (MatchM match : matchList) {
                    List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
                    for (MatchQuestion matchQuestion : matchQuestionList) {
                        matchQuestion.setOrder(i++);
                    }
                    match.setMatchQuestionList(matchQuestionList);
                    match.setSubQuestionNum(matchQuestionList.size());
                    match.setCollectStatus(testMapper.getCollect(account, match.getId(),table) != null);
                }
                return matchList;
            case 4:
                List<CarefulM> carefulList = testMapper.getCarefulWrongList(account);
                for (CarefulM careful : carefulList) {
                    List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
                    for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                        carefulQuestion.setOrder(i++);
                    }
                    careful.setCarefulQuestionList(carefulQuestionList);
                    careful.setSubQuestionNum(carefulQuestionList.size());
                    careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),table) != null);
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationWrongList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                    translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),table) != null);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingWrongList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
                    writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),table) != null);
                }
                return writingList;
            case 7:
                List<ClozeM> clozeList = testMapper.getClozeWrongList(account);
                for (ClozeM cloze : clozeList) {
                    List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
                    for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                        clozeQuestion.setOrder(i++);
                    }
                    cloze.setClozeQuestionList(clozeQuestionList);
                    cloze.setSubQuestionNum(clozeQuestionList.size());
                    cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),table) != null);
                }
                return clozeList;
            case 8:
                List<NewM> newList = testMapper.getNewWrongList(account);
                for (NewM newM : newList) {
                    List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
                    for (NewQuestion newQuestion : newQuestionList) {
                        newQuestion.setOrder(i++);
                    }
                    newM.setNewQuestionList(newQuestionList);
                    newM.setSubQuestionNum(newQuestionList.size());
                    newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),table) != null);
                }
                return newList;
        }
        return null;
    }

    public List<TruePaper> getTestTruePaperList(int questionBankId) {
        return testMapper.getTestTruePaperList(questionBankId);
    }

    public Paper getTruePaper(int truePaperId,String account) {
        Paper paper = new Paper();
        int i = 1;

        List<ListeningM> listeningList = testMapper.getListeningListTrue(truePaperId);
        for (ListeningM listening : listeningList) {
            List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
            for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                listeningQuestion.setOrder(i++);
            }
            listening.setListeningQuestionList(listeningQuestionList);
            listening.setSubQuestionNum(listeningQuestionList.size());
            listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),1) != null);
        }
        paper.setListeningList(listeningList);

        List<BankedM> bankedList = testMapper.getBankedListTrue(truePaperId);
        for (BankedM banked : bankedList) {
            List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
            for (BankedQuestion bankedQuestion : bankedQuestionList) {
                bankedQuestion.setOrder(i++);
            }
            banked.setWordList(banked.getWord().split(","));
            banked.setBankedQuestionList(bankedQuestionList);
            banked.setSubQuestionNum(bankedQuestionList.size());
            banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),2) != null);
        }
        paper.setBankedList(bankedList);

        List<MatchM> matchList = testMapper.getMatchListTrue(truePaperId);
        for (MatchM match : matchList) {
            List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
            for (MatchQuestion matchQuestion : matchQuestionList) {
                matchQuestion.setOrder(i++);
            }
            match.setMatchQuestionList(matchQuestionList);
            match.setSubQuestionNum(matchQuestionList.size());
            match.setCollectStatus(testMapper.getCollect(account, match.getId(),3) != null);
        }
        paper.setMatchList(matchList);

        List<ClozeM> clozeList = testMapper.getClozeListTrue(truePaperId);
        for (ClozeM cloze : clozeList) {
            List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
            for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                clozeQuestion.setOrder(i++);
            }
            cloze.setClozeQuestionList(clozeQuestionList);
            cloze.setSubQuestionNum(clozeQuestionList.size());
            cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),7) != null);
        }
        paper.setClozeList(clozeList);

        List<CarefulM> carefulList = testMapper.getCarefulListTrue(truePaperId);
        for (CarefulM careful : carefulList) {
            List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
            for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                carefulQuestion.setOrder(i++);
            }
            careful.setCarefulQuestionList(carefulQuestionList);
            careful.setSubQuestionNum(carefulQuestionList.size());
            careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),4) != null);
        }
        paper.setCarefulList(carefulList);

        List<NewM> newList = testMapper.getNewListTrue(truePaperId);
        for (NewM newM : newList) {
            List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
            for (NewQuestion newQuestion : newQuestionList) {
                newQuestion.setOrder(i++);
            }
            newM.setNewQuestionList(newQuestionList);
            newM.setSubQuestionNum(newQuestionList.size());
            newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),8) != null);
        }
        paper.setNewList(newList);

        List<TranslationM> translationList = testMapper.getTranslationListTrue(truePaperId);
        for (TranslationM translation : translationList) {
            translation.setOrder(i++);
            translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),5) != null);
        }
        paper.setTranslationList(translationList);

        List<WritingM> writingList = testMapper.getWritingListTrue(truePaperId);
        for (WritingM writing : writingList) {
            writing.setOrder(i++);
            writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),6) != null);
        }
        paper.setWritingList(writingList);

        return paper;
    }

    public List<TestNoteDetail> getTestNoteList(String account, int table) {
        switch (table){
            case 1:
                return testMapper.getListeningNoteList(account);
            case 2:
                return testMapper.getBankedNoteList(account);
            case 3:
                return testMapper.getMatchNoteList(account);
            case 4:
                return testMapper.getCarefulNoteList(account);
            case 5:
                return testMapper.getTranslationNoteList(account);
            case 6:
                return testMapper.getWritingNoteList(account);
            case 7:
                return testMapper.getClozeNoteList(account);
            case 8:
                return testMapper.getNewNoteList(account);
            default:
                return new ArrayList<>();
        }
    }

    public TestNoteDetail getTestNote(String account, int questionId, int table) {
        return testMapper.getTestNote(account, questionId, table);
    }

    public boolean saveTestNote(String account, int questionId, int table, String info) {
        if(testMapper.getTestNote(account,questionId,table)==null) {
            return testMapper.addTestNote(account,questionId,table,info);
        } else {
            return testMapper.setTestNote(account,questionId,table,info);
        }
    }

    public boolean deleteTestNote(String account, int questionId, int table) {
        if(testMapper.getTestNote(account, questionId, table)!=null) {
            return testMapper.removeTestNote(account, questionId,table);
        } else {
            return true;
        }
    }

    public Paper getExam(int questionBankId,String account) {
        Paper paper = new Paper();
        int i = 1;
        String randomS = "order by rand()";
        List<ListeningM> listeningList = new ArrayList<>();
        List<BankedM> bankedList = new ArrayList<>();
        List<MatchM> matchList = new ArrayList<>();
        List<CarefulM> carefulList = new ArrayList<>();
        List<TranslationM> translationList = new ArrayList<>();
        List<WritingM> writingList = new ArrayList<>();
        List<ClozeM> clozeList = new ArrayList<>();
        List<NewM> newList = new ArrayList<>();

        if (questionBankId==1) {
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 2", questionBankId, "and num=2", "and type=1"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 1", questionBankId, "and num=3", "and type=1"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 2", questionBankId, "and num=4", "and type=2"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 2", questionBankId, "and num=3", "and type=3"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 1", questionBankId, "and num=4", "and type=3"));
        } else if (questionBankId==2){
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 2", questionBankId, "and num=4", "and type=2"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 1", questionBankId, "and num=3", "and type=3"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 1", questionBankId, "and num=4", "and type=3"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 2", questionBankId, "and num=3", "and type=4"));
            listeningList.addAll(testMapper.getListeningList(randomS, "limit 1", questionBankId, "and num=4", "and type=4"));
        }
        for (ListeningM listening : listeningList) {
            List<ListeningQuestion> listeningQuestionList = testMapper.getListeningQuestionList(listening.getId());
            for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                listeningQuestion.setOrder(i++);
            }
            listening.setListeningQuestionList(listeningQuestionList);
            listening.setSubQuestionNum(listeningQuestionList.size());
            listening.setCollectStatus(testMapper.getCollect(account, listening.getId(),1) != null);
        }
        paper.setListeningList(listeningList);

        if (questionBankId==1||questionBankId==2) {
            bankedList.addAll(testMapper.getBankedList(randomS, "limit 1", questionBankId));
        }
        for (BankedM banked : bankedList) {
            List<BankedQuestion> bankedQuestionList = testMapper.getBankedQuestionList(banked.getId());
            for (BankedQuestion bankedQuestion : bankedQuestionList) {
                bankedQuestion.setOrder(i++);
            }
            banked.setWordList(banked.getWord().split(","));
            banked.setBankedQuestionList(bankedQuestionList);
            banked.setSubQuestionNum(bankedQuestionList.size());
            banked.setCollectStatus(testMapper.getCollect(account, banked.getId(),2) != null);
        }
        paper.setBankedList(bankedList);

        if (questionBankId==1||questionBankId==2) {
            matchList.addAll(testMapper.getMatchList(randomS, "limit 1", questionBankId));
        }
        for (MatchM match : matchList) {
            List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
            for (MatchQuestion matchQuestion : matchQuestionList) {
                matchQuestion.setOrder(i++);
            }
            match.setMatchQuestionList(matchQuestionList);
            match.setSubQuestionNum(matchQuestionList.size());
            match.setCollectStatus(testMapper.getCollect(account, match.getId(),3) != null);
        }
        paper.setMatchList(matchList);

        if (questionBankId==3||questionBankId==4) {
            clozeList.addAll(testMapper.getClozeList(randomS, "limit 1", questionBankId));
        }
        for (ClozeM cloze : clozeList) {
            List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
            for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                clozeQuestion.setOrder(i++);
            }
            cloze.setClozeQuestionList(clozeQuestionList);
            cloze.setSubQuestionNum(clozeQuestionList.size());
            cloze.setCollectStatus(testMapper.getCollect(account, cloze.getId(),7) != null);
        }
        paper.setClozeList(clozeList);

        if (questionBankId==1||questionBankId==2) {
            carefulList.addAll(testMapper.getCarefulList(randomS, "limit 2", questionBankId));
        } else if (questionBankId==3||questionBankId==4){
            carefulList.addAll(testMapper.getCarefulList(randomS, "limit 4", questionBankId));
        }
        for (CarefulM careful : carefulList) {
            List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
            for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                carefulQuestion.setOrder(i++);
            }
            careful.setCarefulQuestionList(carefulQuestionList);
            careful.setSubQuestionNum(carefulQuestionList.size());
            careful.setCollectStatus(testMapper.getCollect(account, careful.getId(),4) != null);
        }
        paper.setCarefulList(carefulList);

        if (questionBankId==3||questionBankId==4) {
            int randomNumber = new Random().nextInt(3); // 生成0~2之间的随机数
            if (randomNumber == 0) {
                newList.addAll(testMapper.getNewList(randomS, "limit 1", questionBankId, "and type=8"));
            } else if (randomNumber == 1) {
                newList.addAll(testMapper.getNewList(randomS, "limit 1", questionBankId, "and type=9"));
            } else {
                newList.addAll(testMapper.getNewList(randomS, "limit 1", questionBankId, "and type=10"));
            }
        }
        for (NewM newM : newList) {
            List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
            for (NewQuestion newQuestion : newQuestionList) {
                newQuestion.setOrder(i++);
            }
            newM.setNewQuestionList(newQuestionList);
            newM.setSubQuestionNum(newQuestionList.size());
            newM.setCollectStatus(testMapper.getCollect(account, newM.getId(),8) != null);
        }
        paper.setNewList(newList);

        translationList.addAll(testMapper.getTranslationList(randomS,"limit 1",questionBankId));
        for (TranslationM translation : translationList) {
            translation.setOrder(i++);
            translation.setCollectStatus(testMapper.getCollect(account, translation.getId(),5) != null);
        }
        paper.setTranslationList(translationList);

        if (questionBankId==3||questionBankId==4) {
            writingList.addAll(testMapper.getWritingList(randomS, "limit 2", questionBankId,"and type=14"));
        }
        writingList.addAll(testMapper.getWritingList(randomS,"limit 1",questionBankId,"and type=13"));
        for (WritingM writing : writingList) {
            writing.setOrder(i++);
            writing.setCollectStatus(testMapper.getCollect(account, writing.getId(),6) != null);
        }
        paper.setWritingList(writingList);

        return paper;
    }

    public boolean collect(String account, int questionId, int table, boolean collect) {
        if(collect){
            if(testMapper.getCollect(account,questionId,table)==null) {
                return testMapper.addCollect(account, questionId,table);
            } else {
                return true;
            }
        } else {
            if(testMapper.getCollect(account,questionId,table)!=null) {
                return testMapper.removeCollect(account, questionId,table);
            } else {
                return true;
            }
        }
    }
}
