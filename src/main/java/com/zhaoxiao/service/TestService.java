package com.zhaoxiao.service;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.mapper.TestMapper;
import com.zhaoxiao.model.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public List<ListeningM> getListeningList(boolean random, int limitNum, int questionBankId,int num,int type) {
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

        listeningList = testMapper.getListeningList(randomS, limitNumS, questionBankId, numS, typeS);

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
        }
        return listeningList;
    }

    public List<BankedM> getBankedList(boolean random, int limitNum, int questionBankId) {
        List<BankedM> bankedList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        bankedList = testMapper.getBankedList(randomS, limitNumS, questionBankId);

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
        }
        return bankedList;
    }

    public List<MatchM> getMatchList(boolean random, int limitNum, int questionBankId) {
        List<MatchM> matchList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        matchList = testMapper.getMatchList(randomS, limitNumS, questionBankId);

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
        }
        return matchList;
    }

    public List<CarefulM> getCarefulList(boolean random, int limitNum, int questionBankId) {
        List<CarefulM> carefulList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        carefulList = testMapper.getCarefulList(randomS, limitNumS, questionBankId);

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
        }
        return carefulList;
    }

    public List<TranslationM> getTranslationList(boolean random, int limitNum, int questionBankId) {
        List<TranslationM> translationList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        translationList = testMapper.getTranslationList(randomS, limitNumS, questionBankId);

//        if (random) {
//            if (limitNum > 0) translationList = testMapper.getTranslationList("order by rand()", "limit " + limitNum, questionBankId);
//            else translationList = testMapper.getTranslationList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) translationList = testMapper.getTranslationList("", "limit " + limitNum, questionBankId);
//            else translationList = testMapper.getTranslationList("", "", questionBankId);
//        }

        for (TranslationM translation : translationList) {
            translation.setOrder(i++);
        }
        return translationList;
    }

    public List<WritingM> getWritingList(boolean random, int limitNum, int questionBankId, int type) {
        List<WritingM> writingList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        writingList = testMapper.getWritingList(randomS, limitNumS, questionBankId, typeS);

//        if (random) {
//            if (limitNum > 0) writingList = testMapper.getWritingList("order by rand()", "limit " + limitNum, questionBankId);
//            else writingList = testMapper.getWritingList("order by rand()", "", questionBankId);
//        } else {
//            if (limitNum > 0) writingList = testMapper.getWritingList("", "limit " + limitNum, questionBankId);
//            else writingList = testMapper.getWritingList("", "", questionBankId);
//        }

        for (WritingM writing : writingList) {
            writing.setOrder(i++);
        }
        return writingList;
    }

    public List<ClozeM> getClozeList(boolean random, int limitNum, int questionBankId) {
        List<ClozeM> clozeList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        clozeList = testMapper.getClozeList(randomS, limitNumS, questionBankId);

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
        }
        return clozeList;
    }

    public List<NewM> getNewList(boolean random, int limitNum, int questionBankId, int type) {
        List<NewM> newList;
        int i = 1;

        String randomS = "";
        if(random) randomS = "order by rand()";

        String limitNumS = "";
        if(limitNum>0) limitNumS = "limit " + limitNum;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        newList = testMapper.getNewList(randomS, limitNumS, questionBankId, typeS);

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
        }
        return newList;
    }

    public List<TestFtype> getTestFtypeList(int questionBankId) {
        List<TestFtype> ftypes = new ArrayList<>();
        TestFtype ftype1,ftype2,ftype3,ftype4,ftype5;
        List<TestStype> stypes1,stypes2,stypes3,stypes4,stypes5;
        int num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13;

        switch (questionBankId) {
            case 1:
                stypes1 = new ArrayList<>();

                num1 = testMapper.getListeningCount(1, questionBankId);
                stypes1.add(new TestStype(1, "短篇新闻", num1, 1000, 400));

                num2 = testMapper.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = testMapper.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                ftype1 = new TestFtype(1, "听力", stypes1, false, num1+num2+num3, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = testMapper.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = testMapper.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = testMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(0,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 2:
                stypes1 = new ArrayList<>();
                num2 = testMapper.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = testMapper.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                num4 = testMapper.getListeningCount(4, questionBankId);
                stypes1.add(new TestStype(4, "讲话/报道/讲座", num4, 1000, 400));


                ftype1 = new TestFtype(1, "听力", stypes1, false, num2+num3+num4, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = testMapper.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = testMapper.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = testMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(0,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 3:
            case 4:
                stypes5 = new ArrayList<>();

                num10 = testMapper.getClozeCount(questionBankId);
                stypes5.add(new TestStype(15,"完形填空",num10,500,400));

                ftype5 = new TestFtype(5, "完形填空", stypes5, false, num10, 500, 400);


                stypes2 = new ArrayList<>();

                num7 = testMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                num11 = testMapper.getNewCount(1,questionBankId);
                stypes2.add(new TestStype(8, "新题型-七选五", num11, 1000, 400));

                num12 = testMapper.getNewCount(2,questionBankId);
                stypes2.add(new TestStype(9, "新题型-排序", num12, 1000, 400));

                num13 = testMapper.getNewCount(3,questionBankId);
                stypes2.add(new TestStype(10, "新题型-小标题", num13, 1000, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num7+num11+num12+num13, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = testMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(12, "英译汉", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = testMapper.getWritingCount(1,questionBankId);
                stypes4.add(new TestStype(14, "应用文", num9, 1000, 400));

                num9 = testMapper.getWritingCount(2,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


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
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationCollectionList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingCollectionList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
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
                }
                return newList;
        }
        return null;
    }

    public QuestionM getQuestionById(int questionId, int table) {
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
                return banked;
            case 3:
                MatchM match = testMapper.getMatchQuestionById(questionId);
                List<MatchQuestion> matchQuestionList = testMapper.getMatchQuestionList(match.getId());
                for (MatchQuestion matchQuestion : matchQuestionList) {
                    matchQuestion.setOrder(i++);
                }
                match.setMatchQuestionList(matchQuestionList);
                match.setSubQuestionNum(matchQuestionList.size());
                return match;
            case 4:
                CarefulM careful = testMapper.getCarefulQuestionById(questionId);
                List<CarefulQuestion> carefulQuestionList = testMapper.getCarefulQuestionList(careful.getId());
                for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                    carefulQuestion.setOrder(i++);
                }
                careful.setCarefulQuestionList(carefulQuestionList);
                careful.setSubQuestionNum(carefulQuestionList.size());
                return careful;
            case 5:
                TranslationM translation = testMapper.getTranslationQuestionById(questionId);
                translation.setOrder(i++);
                return translation;
            case 6:
                WritingM writing = testMapper.getWritingQuestionById(questionId);
                writing.setOrder(i++);
                return writing;
            case 7:
                ClozeM cloze = testMapper.getClozeQuestionById(questionId);
                List<ClozeQuestion> clozeQuestionList = testMapper.getClozeQuestionList(cloze.getId());
                for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                    clozeQuestion.setOrder(i++);
                }
                cloze.setClozeQuestionList(clozeQuestionList);
                cloze.setSubQuestionNum(clozeQuestionList.size());
                return cloze;
            case 8:
                NewM newM = testMapper.getNewQuestionById(questionId);
                List<NewQuestion> newQuestionList = testMapper.getNewQuestionList(newM.getId());
                for (NewQuestion newQuestion : newQuestionList) {
                    newQuestion.setOrder(i++);
                }
                newM.setNewQuestionList(newQuestionList);
                newM.setSubQuestionNum(newQuestionList.size());
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
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationHistoryList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingHistoryList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
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
                }
                return carefulList;
            case 5:
                List<TranslationM> translationList = testMapper.getTranslationWrongList(account);
                for (TranslationM translation : translationList) {
                    translation.setOrder(i++);
                }
                return translationList;
            case 6:
                List<WritingM> writingList = testMapper.getWritingWrongList(account);
                for (WritingM writing : writingList) {
                    writing.setOrder(i++);
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
                }
                return newList;
        }
        return null;
    }
}
