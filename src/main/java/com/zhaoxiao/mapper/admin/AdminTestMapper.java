package com.zhaoxiao.mapper.admin;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.model.test.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminTestMapper {
    @Select("select id,info,audio,type,questionBankId from listening where questionBankId=#{questionBankId} ${type}")
    List<ListeningM> getListeningList(int questionBankId, String type);

    @Select("select * from listeningQuestion where listeningId = #{listeningId}")
    List<ListeningQuestion> getListeningQuestionList(int listeningId);

    @Select("select id,info,word,questionBankId from banked where questionBankId=#{questionBankId}")
    List<BankedM> getBankedList(int questionBankId);

    @Select("select * from bankedQuestion where bankedId = #{bankedId}")
    List<BankedQuestion> getBankedQuestionList(int bankedId);

    @Select("select id,info,num,questionBankId from `match` where questionBankId=#{questionBankId}")
    List<MatchM> getMatchList(int questionBankId);

    @Select("select * from matchQuestion where matchId = #{matchId}")
    List<MatchQuestion> getMatchQuestionList(int matchId);

    @Select("select id,info,questionBankId from careful where questionBankId=#{questionBankId}")
    List<CarefulM> getCarefulList(int questionBankId);

    @Select("select * from carefulQuestion where carefulId = #{carefulId}")
    List<CarefulQuestion> getCarefulQuestionList(int carefulId);

    @Select("select id,info,answer,questionBankId from translation where questionBankId=#{questionBankId}")
    List<TranslationM> getTranslationList(int questionBankId);

    @Select("select id,info,answer,questionBankId from writing where questionBankId=#{questionBankId} ${type}")
    List<WritingM> getWritingList(int questionBankId, String type);

    @Select("select id,info,questionBankId from cloze where questionBankId=#{questionBankId}")
    List<ClozeM> getClozeList(int questionBankId);

    @Select("select * from clozeQuestion where clozeId = #{clozeId}")
    List<ClozeQuestion> getClozeQuestionList(int clozeId);

    @Select("select id,info,A,B,C,D,E,F,G,type,questionBankId from new where questionBankId=#{questionBankId} ${type}")
    List<NewM> getNewList(int questionBankId, String type);

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

    @Insert("insert into listening(id,info,audio,type,questionBankId) values(#{id},#{info},#{audio},#{type},#{questionBankId})")
    boolean addListening(ListeningM listening);

    @Insert("insert into listeningQuestion(id,listeningId,A,B,C,D,answer) values(#{id},#{listeningId},#{A},#{V},#{C},#{D},#{answer})")
    boolean addListeningQuestion(ListeningQuestion listening);

    @Insert("insert into banked(id,info,word,questionBankId) values(#{id},#{info},#{word},#{questionBankId})")
    boolean addBanked(BankedM banked);

    @Insert("insert into bankedQuestion(id,bankedId,answer) values(#{id},#{bankedId},#{answer})")
    boolean addBankedQuestion(BankedQuestion bankedQuestion);

    @Insert("insert into match(id,info,num,questionBankId) values(#{id},#{info},#{num},#{questionBankId})")
    boolean addMatch(MatchM match);

    @Insert("insert into matchQuestion(id,matchId,stem,answer) values(#{id},#{matchId},#{stem},#{answer})")
    boolean addMatchQuestion(MatchQuestion matchQuestion);

    @Insert("insert into careful(id,info,questionBankId) values(#{id},#{info},#{questionBankId})")
    boolean addCareful(CarefulM careful);

    @Insert("insert into carefulQuestion(id,carefulId,stem,A,B,C,D,answer) values(#{id},#{carefulId},#{stem},#{A},#{B},#{C},#{D},#{answer})")
    boolean addCarefulQuestion(CarefulQuestion carefulQuestion);

    @Insert("insert into translation(id,info,answer,questionBankId) values(#{id},#{info},#{answer},#{questionBankId})")
    boolean addTranslation(TranslationM translation);

    @Insert("insert into writing(id,info,img,type,answer,questionBankId) values(#{id},#{info},#{img},#{type},#{answer},#{questionBankId})")
    boolean addWriting(WritingM writing);

    @Insert("insert into cloze(id,info,questionBankId) values(#{id},#{info},#{questionBankId})")
    boolean addCloze(ClozeM cloze);

    @Insert("insert into clozeQuestion(id,clozeId,A,B,C,D,answer) values(#{id},#{clozeId},#{A},#{B},#{C},#{D},#{answer})")
    boolean addClozeQuestion(ClozeQuestion carefulQuestion);

    @Insert("insert into new(id,info,A,B,C,D,E,F,G,type,questionBankId) values(#{id},#{info},#{A},#{B},#{C},#{D},#{E},#{F},#{G},#{type},#{questionBankId})")
    boolean addNew(NewM newM);

    @Insert("insert into newQuestion(id,newId,answer) values(#{id},#{newId},#{answer})")
    boolean addNewQuestion(NewQuestion newQuestion);
}
