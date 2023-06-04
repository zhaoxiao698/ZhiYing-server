package com.zhaoxiao.mapper.admin;

import com.zhaoxiao.entity.test.*;
import com.zhaoxiao.model.test.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminTestMapperNew {
    @Select("select id,info,audio,type,questionBankId from listening where questionBankId=#{questionBankId} ${type}")
    List<QuestionNew> getListeningList(int questionBankId, String type);

    @Select("select id,listeningId superQuestionId,A,B,C,D,answer from listeningQuestion where listeningId = #{listeningId}")
    List<SubQuestionNew> getListeningQuestionList(int listeningId);

    @Select("select id,info,word,type,questionBankId from banked where questionBankId=#{questionBankId}")
    List<QuestionNew> getBankedList(int questionBankId);

    @Select("select id,bankedId superQuestionId,answer from bankedQuestion where bankedId = #{bankedId}")
    List<SubQuestionNew> getBankedQuestionList(int bankedId);

    @Select("select id,info,num,type,questionBankId from `match` where questionBankId=#{questionBankId}")
    List<QuestionNew> getMatchList(int questionBankId);

    @Select("select id,matchId superQuestionId,stem,answer from matchQuestion where matchId = #{matchId}")
    List<SubQuestionNew> getMatchQuestionList(int matchId);

    @Select("select id,info,type,questionBankId from careful where questionBankId=#{questionBankId}")
    List<QuestionNew> getCarefulList(int questionBankId);

    @Select("select id,carefulId superQuestionId,stem,A,B,C,D,answer from carefulQuestion where carefulId = #{carefulId}")
    List<SubQuestionNew> getCarefulQuestionList(int carefulId);

    @Select("select id,info,answer,type,questionBankId from translation where questionBankId=#{questionBankId}")
    List<QuestionNew> getTranslationList(int questionBankId);

    @Select("select id,info,answer,type,questionBankId,img from writing where questionBankId=#{questionBankId} ${type}")
    List<QuestionNew> getWritingList(int questionBankId, String type);

    @Select("select id,info,type,questionBankId from cloze where questionBankId=#{questionBankId}")
    List<QuestionNew> getClozeList(int questionBankId);

    @Select("select id,clozeId superQuestionId,A,B,C,D,answer from clozeQuestion where clozeId = #{clozeId}")
    List<SubQuestionNew> getClozeQuestionList(int clozeId);

    @Select("select id,info,A,B,C,D,E,F,G,type,questionBankId from new where questionBankId=#{questionBankId} ${type}")
    List<QuestionNew> getNewList(int questionBankId, String type);

    @Select("select id,newId superQuestionId,answer from newQuestion where newId = #{newId}")
    List<SubQuestionNew> getNewQuestionList(int newId);

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

    @Insert("insert into listening(info,audio,type,questionBankId) values(#{info},#{audio},#{type},#{questionBankId})")
    boolean addListening(QuestionNew listening);

    @Insert("insert into listeningQuestion(listeningId,A,B,C,D,answer) values(#{superQuestionId},#{A},#{B},#{C},#{D},#{answer})")
    boolean addListeningQuestion(SubQuestionNew listening);

    @Insert("insert into banked(info,word,questionBankId) values(#{info},#{word},#{questionBankId})")
    boolean addBanked(QuestionNew banked);

    @Insert("insert into bankedQuestion(bankedId,answer) values(#{superQuestionId},#{answer})")
    boolean addBankedQuestion(SubQuestionNew bankedQuestion);

    @Insert("insert into `match`(info,num,questionBankId) values(#{info},#{num},#{questionBankId})")
    boolean addMatch(QuestionNew match);

    @Insert("insert into matchQuestion(matchId,stem,answer) values(#{superQuestionId},#{stem},#{answer})")
    boolean addMatchQuestion(SubQuestionNew matchQuestion);

    @Insert("insert into careful(info,questionBankId) values(#{info},#{questionBankId})")
    boolean addCareful(QuestionNew careful);

    @Insert("insert into carefulQuestion(carefulId,stem,A,B,C,D,answer) values(#{superQuestionId},#{stem},#{A},#{B},#{C},#{D},#{answer})")
    boolean addCarefulQuestion(SubQuestionNew carefulQuestion);

    @Insert("insert into translation(info,answer,questionBankId,type) values(#{info},#{answer},#{questionBankId},#{type})")
    boolean addTranslation(QuestionNew translation);

    @Insert("insert into writing(info,img,type,answer,questionBankId) values(#{info},#{img},#{type},#{answer},#{questionBankId})")
    boolean addWriting(QuestionNew writing);

    @Insert("insert into cloze(info,questionBankId) values(#{info},#{questionBankId})")
    boolean addCloze(QuestionNew cloze);

    @Insert("insert into clozeQuestion(clozeId,A,B,C,D,answer) values(#{superQuestionId},#{A},#{B},#{C},#{D},#{answer})")
    boolean addClozeQuestion(SubQuestionNew carefulQuestion);

    @Insert("insert into new(info,A,B,C,D,E,F,G,type,questionBankId) values(#{info},#{A},#{B},#{C},#{D},#{E},#{F},#{G},#{type},#{questionBankId})")
    boolean addNew(QuestionNew newM);

    @Insert("insert into newQuestion(newId,answer) values(#{superQuestionId},#{answer})")
    boolean addNewQuestion(SubQuestionNew newQuestion);




    @Update("update listening set info=#{info},audio=#{audio} where id=#{id}")
    boolean setListening(QuestionNew listening);

    @Update("update listening set info=#{info} where id=#{id}")
    boolean setListeningNoAudio(QuestionNew listening);

    @Update("update listeningQuestion set A=#{A},B=#{B},C=#{C},D=#{D},answer=#{answer} where id=#{id}")
    boolean setListeningQuestion(SubQuestionNew listening);

    @Update("update banked set info=#{info},word=#{word} where id=#{id}")
    boolean setBanked(QuestionNew banked);

    @Update("update bankedQuestion set answer=#{answer} where id=#{id}")
    boolean setBankedQuestion(SubQuestionNew bankedQuestion);

    @Update("update `match` set info=#{info},num=#{num} where id=#{id}")
    boolean setMatch(QuestionNew match);

    @Update("update matchQuestion set stem=#{stem},answer=#{answer} where id=#{id}")
    boolean setMatchQuestion(SubQuestionNew matchQuestion);

    @Update("update careful set info=#{info} where id=#{id}")
    boolean setCareful(QuestionNew careful);

    @Update("update carefulQuestion set stem=#{stem},A=#{A},B=#{B},C=#{C},D=#{D},answer=#{answer} where id=#{id}")
    boolean setCarefulQuestion(SubQuestionNew carefulQuestion);

    @Update("update translation set info=#{info},answer=#{answer} where id=#{id}")
    boolean setTranslation(QuestionNew translation);

    @Update("update writing set info=#{info},img=#{img},answer=#{answer} where id=#{id}")
    boolean setWriting(QuestionNew writing);

    @Update("update writing set info=#{info},answer=#{answer} where id=#{id}")
    boolean setWritingNoImg(QuestionNew writing);

    @Update("update cloze set info=#{info} where id=#{id}")
    boolean setCloze(QuestionNew cloze);

    @Update("update clozeQuestion set A=#{A},B=#{B},C=#{C},D=#{D},answer=#{answer} where id=#{id}")
    boolean setClozeQuestion(SubQuestionNew carefulQuestion);

    @Update("update new set info=#{info},A=#{A},B=#{B},C=#{C},D=#{D},E=#{E},F=#{F},G=#{G} where id=#{id}")
    boolean setNew(QuestionNew newM);

    @Update("update newQuestion set answer=#{answer}")
    boolean setNewQuestion(SubQuestionNew newQuestion);

    @Select("select audio from listening where id=#{id}")
    String getListeningAudio(int id);

    @Select("select img from writing where id=#{id}")
    String getWritingImg(int id);

    @Delete("delete from listening where id=#{id}")
    boolean removeListening(int id);

    @Delete("delete from listeningQuestion where id=#{id}")
    boolean removeListeningQuestion(int id);

    @Delete("delete from banked where id=#{id}")
    boolean removeBanked(int id);

    @Delete("delete from bankedQuestion where id=#{id}")
    boolean removeBankedQuestion(int id);

    @Delete("delete from `match` where id=#{id}")
    boolean removeMatch(int id);

    @Delete("delete from `matchQuestion` where id=#{id}")
    boolean removeMatchQuestion(int id);

    @Delete("delete from careful where id=#{id}")
    boolean removeCareful(int id);

    @Delete("delete from carefulQuestion where id=#{id}")
    boolean removeCarefulQuestion(int id);

    @Delete("delete from translation where id=#{id}")
    boolean removeTranslation(int id);

    @Delete("delete from writing where id=#{id}")
    boolean removeWriting(int id);

    @Delete("delete from cloze where id=#{id}")
    boolean removeCloze(int id);

    @Delete("delete from clozeQuestion where id=#{id}")
    boolean removeClozeQuestion(int id);

    @Delete("delete from `new` where id=#{id}")
    boolean removeNew(int id);

    @Delete("delete from `newQuestion` where id=#{id}")
    boolean removeNewQuestion(int id);

    @Delete("delete from `listeningQuestion` where listeningId=#{id}")
    void removeListeningSub(int id);

    @Delete("delete from `bankedQuestion` where bankedId=#{id}")
    void removeBankedSub(int id);

    @Delete("delete from `matchQuestion` where matchId=#{id}")
    void removeMatchSub(int id);

    @Delete("delete from `carefulQuestion` where carefulId=#{id}")
    void removeCarefulSub(int id);

//    @Delete("delete from `translationQuestion` where translationId=#{id}")
//    void removeTranslationSub(int id);
//
//    @Delete("delete from `writingQuestion` where writingId=#{id}")
//    void removeWritingSub(int id);

    @Delete("delete from `clozeQuestion` where clozeId=#{id}")
    void removeClozeSub(int id);

    @Delete("delete from `newQuestion` where newId=#{id}")
    void removeNewSub(int id);

    @Select("select * from questionBank")
    List<QuestionBank> getQuestionBankList();

    @Update("update listening set num=num+1 where id=#{id}")
    void addListeningQuestionNum(int id);

    @Select("select listeningId from listeningQuestion where id=#{id}")
    int getSuperQuestionId(int id);

    @Update("update listening set num=num-1 where id=#{id}")
    void subListeningQuestionNum(int superQuestionId);
}
