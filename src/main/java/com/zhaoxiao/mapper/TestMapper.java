package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.model.test.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TestMapper {
    @Select("select id,info,audio,type from listening where questionBankId=#{questionBankId} ${num} ${type} ${order} ${limit}")
    List<ListeningM> getListeningList(String order, String limit, int questionBankId, String num, String type);

    @Select("select * from listeningQuestion where listeningId = #{listeningId}")
    List<ListeningQuestion> getListeningQuestionList(int listeningId);

    @Select("select id,info,word from banked where questionBankId=#{questionBankId} ${order} ${limit}")
    List<BankedM> getBankedList(String order, String limit, int questionBankId);

    @Select("select * from bankedQuestion where bankedId = #{bankedId}")
    List<BankedQuestion> getBankedQuestionList(int bankedId);

    @Select("select id,info,num from `match` where questionBankId=#{questionBankId} ${order} ${limit}")
    List<MatchM> getMatchList(String order, String limit, int questionBankId);

    @Select("select * from matchQuestion where matchId = #{matchId}")
    List<MatchQuestion> getMatchQuestionList(int matchId);

    @Select("select id,info from careful where questionBankId=#{questionBankId} ${order} ${limit}")
    List<CarefulM> getCarefulList(String order, String limit, int questionBankId);

    @Select("select * from carefulQuestion where carefulId = #{carefulId}")
    List<CarefulQuestion> getCarefulQuestionList(int carefulId);

    @Select("select id,info,answer from translation where questionBankId=#{questionBankId} ${order} ${limit}")
    List<TranslationM> getTranslationList(String order, String limit, int questionBankId);

    @Select("select id,info,answer from writing where questionBankId=#{questionBankId} ${type} ${order} ${limit}")
    List<WritingM> getWritingList(String order, String limit, int questionBankId, String type);

    @Select("select id,info from cloze where questionBankId=#{questionBankId} ${order} ${limit}")
    List<ClozeM> getClozeList(String order, String limit, int questionBankId);

    @Select("select * from clozeQuestion where clozeId = #{clozeId}")
    List<ClozeQuestion> getClozeQuestionList(int clozeId);

    @Select("select id,info,A,B,C,D,E,F,G,type from new where questionBankId=#{questionBankId} ${type} ${order} ${limit}")
    List<NewM> getNewList(String order, String limit, int questionBankId, String type);

    @Select("select * from newQuestion where newId = #{newId}")
    List<NewQuestion> getNewQuestionList(int newId);

    @Select("select count(*) from listening join listeningQuestion on listening.id=listeningQuestion.listeningId where type = #{type} and questionBankId=#{questionBankId}")
    int getListeningCount(int type, int questionBankId);

    @Select("select count(*) from banked join bankedQuestion on banked.id=bankedQuestion.bankedId where questionBankId=#{questionBankId}")
    int getBankedCount(int questionBankId);

    @Select("select count(*) from `match` join matchQuestion on match.id=matchQuestion.matchId where questionBankId=#{questionBankId}")
    int getMatchCount(int questionBankId);

    @Select("select count(*) from careful join carefulQuestion on careful.id=carefulQuestion.carefulId where questionBankId=#{questionBankId}")
    int getCarefulCount(int questionBankId);

    @Select("select count(*) from translation where questionBankId=#{questionBankId}")
    int getTranslationCount(int questionBankId);

    @Select("select count(*) from writing where type = #{type} and questionBankId=#{questionBankId}")
    int getWritingCount(int type, int questionBankId);

    @Select("select count(*) from cloze join clozeQuestion on cloze.id=clozeQuestion.clozeId where questionBankId=#{questionBankId}")
    int getClozeCount(int questionBankId);

    @Select("select count(*) from new join newQuestion on new.id=newQuestion.newId where type = #{type} and questionBankId=#{questionBankId}")
    int getNewCount(int type, int questionBankId);

    @Select("select l.id,info,audio,type,name subType from listening l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=1 and userAccount=#{account} order by t.addTime desc")
    List<ListeningM> getListeningCollectionList(String account);

    @Select("select l.id,info,word,name subType from banked l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=2 and userAccount=#{account} order by t.addTime desc")
    List<BankedM> getBankedCollectionList(String account);

    @Select("select l.id,info,num,name subType from `match` l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=3 and userAccount=#{account} order by t.addTime desc")
    List<MatchM> getMatchCollectionList(String account);

    @Select("select l.id,info,name subType from careful l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=4 and userAccount=#{account} order by t.addTime desc")
    List<CarefulM> getCarefulCollectionList(String account);

    @Select("select l.id,info,answer,name subType from translation l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=5 and userAccount=#{account} order by t.addTime desc")
    List<TranslationM> getTranslationCollectionList(String account);

    @Select("select l.id,info,answer,name subType from writing l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=6 and userAccount=#{account} order by t.addTime desc")
    List<WritingM> getWritingCollectionList(String account);

    @Select("select l.id,info,name subType from cloze l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=7 and userAccount=#{account} order by t.addTime desc")
    List<ClozeM> getClozeCollectionList(String account);

    @Select("select l.id,info,A,B,C,D,E,F,G,type,name subType from new l join testCollection t on l.id=t.questionId join subType s on l.type=s.id where `table`=8 and userAccount=#{account} order by t.addTime desc")
    List<NewM> getNewCollectionList(String account);

    @Select("select l.id,info,audio,type,name subType from listening l join subType s on l.type=s.id where l.id=#{questionId}")
    ListeningM getListeningQuestionById(int questionId);

    @Select("select l.id,info,word,name subType from banked l join subType s on l.type=s.id where l.id=#{questionId}")
    BankedM getBankedQuestionById(int questionId);

    @Select("select l.id,info,num,name subType from `match` l join subType s on l.type=s.id where l.id=#{questionId}")
    MatchM getMatchQuestionById(int questionId);

    @Select("select l.id,info,name subType from careful l join subType s on l.type=s.id where l.id=#{questionId}")
    CarefulM getCarefulQuestionById(int questionId);

    @Select("select l.id,info,answer,name subType from translation l join subType s on l.type=s.id where l.id=#{questionId}")
    TranslationM getTranslationQuestionById(int questionId);

    @Select("select l.id,info,answer,name subType from writing l join subType s on l.type=s.id where l.id=#{questionId}")
    WritingM getWritingQuestionById(int questionId);

    @Select("select l.id,info,name subType from cloze l join subType s on l.type=s.id where l.id=#{questionId}")
    ClozeM getClozeQuestionById(int questionId);

    @Select("select l.id,info,A,B,C,D,E,F,G,type,name subType from new l join subType s on l.type=s.id where l.id=#{questionId}")
    NewM getNewQuestionById(int questionId);
}
