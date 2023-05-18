package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.study.ArticleNote;
import com.zhaoxiao.entity.study.Banner;
import com.zhaoxiao.entity.study.Sentence;
import com.zhaoxiao.entity.study.Stype;
import com.zhaoxiao.model.study.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface StudyMapper {
    @Select("select * from banner where type!=0 or type!=null")
    List<Banner> getBanner();

    @Select("select a.id,name channelName,title,a.img,duration,addTime from article a join channel c on a.channelId=c.id " +
            "order by addTime desc")
    List<Recent> getRecentList();

    @Select("select c.id,f.name ftypeName,s.name stypeName,c.name,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id order by collection desc")
    List<Hot> getHotList();

    @Select("select * from ftype")
    List<Ftype> getFtypeList();

    @Select("select * from ftype where id = #{ftypeId}")
    Ftype getFtypeById(int ftypeId);

    @Select("select * from stype where ftypeId=#{ftypeId}")
    List<Stype> getStypeList(int ftypeId);

    @Select("select c.id,f.name ftypeName,s.name stypeName,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where stypeId=#{stypeId}")
    List<ChannelM> getChannelList(int stypeId);

    @Select("select c.id,f.name ftypeName,s.name stypeName,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where c.id=#{channelId}")
    ChannelM getChannelById(int channelId);

    @Select("select c.id,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where ftypeId=#{ftypeId}")
    List<ChannelM> getChannelListAll(int ftypeId);

    @Select("select a.id,title,a.img,duration,count,a.collection,addTime from article a join channel c on a.channelId=c.id " +
            "where c.id=#{channelId} order by ${sort} ${order}")
    List<ArticleM> getArticleList(int channelId, String sort, String order);

    @Select("select article.id,channelId,channel.name channelName,title,article.img,duration,audio,video,count,article.collection,addTime from article join channel on article.channelId=channel.id where article.id=#{articleId}")
    ArticleDetailM getArticle(int articleId);

    @Select("select id,articleId,`order`,english,translation,node,`first` from sentence where articleId=#{articleId} order by `order`")
    List<Sentence> getSentenceList(int articleId);

    @Insert("insert into articleRecord(userAccount,articleId) values(#{account},#{articleId})")
    void addArticleRecord(String account, int articleId);

    @Update("update articleRecord set addTime=#{addTime} where userAccount=#{account} and articleId=#{articleId}")
    void setArticleRecord(String account, int articleId, Date addTime);

    @Select("select userAccount from articleRecord where userAccount=#{account} and articleId=#{articleId}")
    String getArticleRecord(String account, int articleId);

    @Delete("delete from articleRecord where userAccount=#{account} and articleId=#{articleId}")
    String removeArticleRecord(String account, int articleId);

    @Select("select userAccount from articleCollection where userAccount=#{account} and articleId=#{articleId}")
    String getCollect(String account, int articleId);

    @Insert("insert into articleCollection(userAccount,articleId) values(#{account},#{articleId})")
    boolean addCollect(String account, int articleId);

    @Delete("delete from articleCollection where userAccount=#{account} and articleId=#{articleId}")
    boolean removeCollect(String account, int articleId);

    @Select("select * from articleNote where userAccount=#{account} and articleId=#{articleId}")
    ArticleNote getNote(String account, int articleId);

    @Insert("insert into articleNote(userAccount,articleId,info) values(#{account},#{articleId},#{info})")
    boolean addNote(String account, int articleId, String info);

    @Delete("delete from articleNote where userAccount=#{account} and articleId=#{articleId}")
    boolean removeNote(String account, int articleId);

    @Update("update articleNote set info=#{info} where userAccount=#{account} and articleId=#{articleId}")
    boolean setNote(String account, int articleId, String info);

    @Select("select a.id,title,a.img,duration,count,a.collection,a.addTime from article a join channel c on a.channelId=c.id " +
            "join articleCollection ac on a.id=ac.articleId where ac.userAccount=#{account} order by ac.addTime desc")
    List<ArticleM> getArticleCollectionList(String account);

    @Select("select c.id,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "channelCollection cc on c.id=cc.channelId where cc.userAccount=#{account} order by cc.addTime desc")
    List<ChannelM> getChannelCollectionList(String account);

    @Select("select a.id,title,a.img,duration,count,a.collection,a.addTime,ac.addTime historyTime from article a join channel c on a.channelId=c.id " +
            "join articleRecord ac on a.id=ac.articleId where ac.userAccount=#{account} order by ac.addTime desc")
    List<ArticleM> getArticleHistoryList(String account);

    @Select("select an.userAccount,an.articleId,an.info,an.addTime,a.title articleTitle,a.img articleImg,c.name channelName from articleNote an join article a on an.articleId=a.id join channel c on a.channelId=c.id where an.userAccount=#{account}")
    List<ArticleNoteDetail> getArticleNoteList(String account);

    @Select("select 1 from channelCollection where userAccount=#{account} and channelId=#{channelId}")
    Integer getChannelCollect(String account, int channelId);

    @Insert("insert into channelCollection(userAccount,channelId) values(#{account},#{channelId})")
    boolean addChannelCollect(String account, int channelId);

    @Delete("delete from channelCollection where userAccount=#{account} and channelId=#{channelId}")
    boolean removeChannelCollect(String account, int channelId);

    @Update("update article set collection=collection+1 where id=#{articleId}")
    void addArticleCollectionNum(int articleId);

    @Update("update article set collection=collection-1 where id=#{articleId}")
    void subArticleCollectionNum(int articleId);

    @Update("update channel set collection=collection+1 where id=#{channelId}")
    void addChannelCollectionNum(int channelId);

    @Update("update channel set collection=collection-1 where id=#{channelId}")
    void subChannelCollectionNum(int channelId);

    @Update("update article set count=count+1 where id=#{articleId}")
    void addArticlePlayNum(int articleId);

    @Select("select a.id,title,a.img,duration,count,a.collection,addTime from article a " +
            "where title like concat('%',#{searchWord},'%')")
    List<ArticleM> getArticleSearchList(String searchWord);
}
