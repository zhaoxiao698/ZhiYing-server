package com.zhaoxiao.service.admin;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.mapper.admin.AdminTestMapper;
import com.zhaoxiao.model.test.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminTestService {
    @Autowired
    private AdminTestMapper adminTestMapper;

    public List<ListeningM> getListeningList(int questionBankId, int type) {
        List<ListeningM> listeningList;
        int i = 1;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        listeningList = adminTestMapper.getListeningList(questionBankId, typeS);

        for (ListeningM listening : listeningList) {
            List<ListeningQuestion> listeningQuestionList = adminTestMapper.getListeningQuestionList(listening.getId());
            for (ListeningQuestion listeningQuestion : listeningQuestionList) {
                listeningQuestion.setOrder(i++);
            }
            listening.setListeningQuestionList(listeningQuestionList);
            listening.setSubQuestionNum(listeningQuestionList.size());
        }
        return listeningList;
    }

    public List<BankedM> getBankedList(int questionBankId) {
        List<BankedM> bankedList;
        int i = 1;

        bankedList = adminTestMapper.getBankedList(questionBankId);

        for (BankedM banked : bankedList) {
            List<BankedQuestion> bankedQuestionList = adminTestMapper.getBankedQuestionList(banked.getId());
            for (BankedQuestion bankedQuestion : bankedQuestionList) {
                bankedQuestion.setOrder(i++);
            }
            banked.setWordList(banked.getWord().split(","));
            banked.setBankedQuestionList(bankedQuestionList);
            banked.setSubQuestionNum(bankedQuestionList.size());
        }
        return bankedList;
    }

    public List<MatchM> getMatchList(int questionBankId) {
        List<MatchM> matchList;
        int i = 1;

        matchList = adminTestMapper.getMatchList(questionBankId);

        for (MatchM match : matchList) {
            List<MatchQuestion> matchQuestionList = adminTestMapper.getMatchQuestionList(match.getId());
            for (MatchQuestion matchQuestion : matchQuestionList) {
                matchQuestion.setOrder(i++);
            }
            match.setMatchQuestionList(matchQuestionList);
            match.setSubQuestionNum(matchQuestionList.size());
        }
        return matchList;
    }

    public List<CarefulM> getCarefulList(int questionBankId) {
        List<CarefulM> carefulList;
        int i = 1;

        carefulList = adminTestMapper.getCarefulList(questionBankId);

        for (CarefulM careful : carefulList) {
            List<CarefulQuestion> carefulQuestionList = adminTestMapper.getCarefulQuestionList(careful.getId());
            for (CarefulQuestion carefulQuestion : carefulQuestionList) {
                carefulQuestion.setOrder(i++);
            }
            careful.setCarefulQuestionList(carefulQuestionList);
            careful.setSubQuestionNum(carefulQuestionList.size());
        }
        return carefulList;
    }

    public List<TranslationM> getTranslationList(int questionBankId) {
        List<TranslationM> translationList;
        int i = 1;

        translationList = adminTestMapper.getTranslationList(questionBankId);

        for (TranslationM translation : translationList) {
            translation.setOrder(i++);
        }
        return translationList;
    }

    public List<WritingM> getWritingList(int questionBankId, int type) {
        List<WritingM> writingList;
        int i = 1;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        writingList = adminTestMapper.getWritingList(questionBankId, typeS);

        for (WritingM writing : writingList) {
            writing.setOrder(i++);
        }
        return writingList;
    }

    public List<ClozeM> getClozeList(int questionBankId) {
        List<ClozeM> clozeList;
        int i = 1;

        clozeList = adminTestMapper.getClozeList(questionBankId);

        for (ClozeM cloze : clozeList) {
            List<ClozeQuestion> clozeQuestionList = adminTestMapper.getClozeQuestionList(cloze.getId());
            for (ClozeQuestion clozeQuestion : clozeQuestionList) {
                clozeQuestion.setOrder(i++);
            }
            cloze.setClozeQuestionList(clozeQuestionList);
            cloze.setSubQuestionNum(clozeQuestionList.size());
        }
        return clozeList;
    }

    public List<NewM> getNewList(int questionBankId, int type) {
        List<NewM> newList;
        int i = 1;

        String typeS = "";
        if(type>0) typeS = "and type=" + type;

        newList = adminTestMapper.getNewList(questionBankId, typeS);

        for (NewM newM : newList) {
            List<NewQuestion> newQuestionList = adminTestMapper.getNewQuestionList(newM.getId());
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

                num1 = adminTestMapper.getListeningCount(1, questionBankId);
                stypes1.add(new TestStype(1, "短篇新闻", num1, 1000, 400));

                num2 = adminTestMapper.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = adminTestMapper.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                ftype1 = new TestFtype(1, "听力", stypes1, false, num1+num2+num3, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = adminTestMapper.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = adminTestMapper.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = adminTestMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapper.getWritingCount(0,questionBankId);
                stypes4.add(new TestStype(13, "短文写作", num9, 1000, 400));

                ftype4 = new TestFtype(4, "写作", stypes4, false, num9, 500, 400);


                ftypes.add(ftype1);
                ftypes.add(ftype2);
                ftypes.add(ftype3);
                ftypes.add(ftype4);

                break;

            case 2:
                stypes1 = new ArrayList<>();
                num2 = adminTestMapper.getListeningCount(2, questionBankId);
                stypes1.add(new TestStype(2, "长对话", num2, 500, 400));

                num3 = adminTestMapper.getListeningCount(3, questionBankId);
                stypes1.add(new TestStype(3, "听力篇章", num3, 500, 400));

                num4 = adminTestMapper.getListeningCount(4, questionBankId);
                stypes1.add(new TestStype(4, "讲话/报道/讲座", num4, 1000, 400));


                ftype1 = new TestFtype(1, "听力", stypes1, false, num2+num3+num4, 500, 400);


                stypes2 = new ArrayList<>();

                num5 = adminTestMapper.getBankedCount(questionBankId);
                stypes2.add(new TestStype(5, "选词填空", num5, 1000, 400));

                num6 = adminTestMapper.getMatchCount(questionBankId);
                stypes2.add(new TestStype(6, "匹配", num6, 500, 400));

                num7 = adminTestMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num5+num6+num7, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(11, "汉译英", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapper.getWritingCount(0,questionBankId);
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

                num10 = adminTestMapper.getClozeCount(questionBankId);
                stypes5.add(new TestStype(15,"完形填空",num10,500,400));

                ftype5 = new TestFtype(5, "完形填空", stypes5, false, num10, 500, 400);


                stypes2 = new ArrayList<>();

                num7 = adminTestMapper.getCarefulCount(questionBankId);
                stypes2.add(new TestStype(7, "仔细阅读", num7, 500, 400));

                num11 = adminTestMapper.getNewCount(1,questionBankId);
                stypes2.add(new TestStype(8, "新题型-七选五", num11, 1000, 400));

                num12 = adminTestMapper.getNewCount(2,questionBankId);
                stypes2.add(new TestStype(9, "新题型-排序", num12, 1000, 400));

                num13 = adminTestMapper.getNewCount(3,questionBankId);
                stypes2.add(new TestStype(10, "新题型-小标题", num13, 1000, 400));

                ftype2 = new TestFtype(2, "阅读理解", stypes2, false, num7+num11+num12+num13, 500, 400);


                stypes3 = new ArrayList<>();

                num8 = adminTestMapper.getTranslationCount(questionBankId);
                stypes3.add(new TestStype(12, "英译汉", num8, 1000, 400));

                ftype3 = new TestFtype(3, "翻译", stypes3, false, num8, 500, 400);


                stypes4 = new ArrayList<>();

                num9 = adminTestMapper.getWritingCount(1,questionBankId);
                stypes4.add(new TestStype(14, "应用文", num9, 1000, 400));

                num9 = adminTestMapper.getWritingCount(2,questionBankId);
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

    public boolean addListening(ListeningM listening) {
        return adminTestMapper.addListening(listening);
    }

    public boolean addListeningQuestion(ListeningQuestion listening) {
        return adminTestMapper.addListeningQuestion(listening);
    }

    public boolean addBanked(BankedM banked) {
        return adminTestMapper.addBanked(banked);
    }

    public boolean addBankedQuestion(BankedQuestion bankedQuestion) {
        return adminTestMapper.addBankedQuestion(bankedQuestion);
    }

    public boolean addMatch(MatchM match) {
        return adminTestMapper.addMatch(match);
    }

    public boolean addMatchQuestion(MatchQuestion matchQuestion) {
        return adminTestMapper.addMatchQuestion(matchQuestion);
    }

    public boolean addCareful(CarefulM careful) {
        return adminTestMapper.addCareful(careful);
    }

    public boolean addCarefulQuestion(CarefulQuestion carefulQuestion) {
        return adminTestMapper.addCarefulQuestion(carefulQuestion);
    }

    public boolean addTranslation(TranslationM translation) {
        return adminTestMapper.addTranslation(translation);
    }

    public boolean addWriting(WritingM writing) {
        return adminTestMapper.addWriting(writing);
    }

    public boolean addCloze(ClozeM cloze) {
        return adminTestMapper.addCloze(cloze);
    }

    public boolean addClozeQuestion(ClozeQuestion clozeQuestion) {
        return adminTestMapper.addClozeQuestion(clozeQuestion);
    }

    public boolean addNew(NewM newM) {
        return adminTestMapper.addNew(newM);
    }

    public boolean addNewQuestion(NewQuestion newQuestion) {
        return adminTestMapper.addNewQuestion(newQuestion);
    }
}
