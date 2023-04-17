package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.study.Banner;
import com.zhaoxiao.entity.study.Sentence;
import com.zhaoxiao.entity.study.Stype;
import com.zhaoxiao.model.study.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("select id,title,img,duration,audio,video,count,collection,addTime from article where id=#{articleId}")
    ArticleDetailM getArticle(int articleId);

    @Select("select id,articleId,`order`,english,translation,node,`first` from sentence where articleId=#{articleId}")
    List<Sentence> getSentenceList(int articleId);

    @Insert("insert into articleRecord(userAccount,articleId) values(#{account},#{articleId})")
    void addArticleRecord(String account, int articleId);

    @Update("update articleRecord set addTime=#{addTime} where userAccount=#{account} and articleId=#{articleId}")
    void setArticleRecord(String account, int articleId, Date addTime);

    @Select("select userAccount from articleRecord where userAccount=#{account} and articleId=#{articleId}")
    String getArticleRecord(String account, int articleId);
}
