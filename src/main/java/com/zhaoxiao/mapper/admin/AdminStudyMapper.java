package com.zhaoxiao.mapper.admin;

import com.zhaoxiao.entity.study.Article;
import com.zhaoxiao.entity.study.Channel;
import com.zhaoxiao.entity.study.Sentence;
import com.zhaoxiao.entity.study.Stype;
import com.zhaoxiao.model.study.ArticleDetailM;
import com.zhaoxiao.model.study.ArticleM;
import com.zhaoxiao.model.study.ChannelM;
import com.zhaoxiao.model.study.Ftype;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminStudyMapper {
    @Select("select * from ftype")
    List<Ftype> getFtypeList();

    @Select("select * from ftype where id = #{ftypeId}")
    Ftype getFtypeById(int ftypeId);

    @Select("select * from stype where ftypeId=#{ftypeId}")
    List<Stype> getStypeList(int ftypeId);

    @Select("select c.id,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where ftypeId=#{ftypeId}")
    List<ChannelM> getChannelListAll(int ftypeId);

    @Select("select c.id,f.name ftypeName,s.name stypeName,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where stypeId=#{stypeId}")
    List<ChannelM> getChannelList(int stypeId);

    @Select("select c.id,f.name ftypeName,s.name stypeName,c.name,c.info,c.img,c.num,c.collection,c.lastTime from channel c join " +
            "stype s on c.stypeId=s.id join ftype f on s.ftypeId=f.id where c.id=#{channelId}")
    ChannelM getChannelById(int channelId);

    @Select("select a.id,title,a.img,duration,count,a.collection,addTime from article a join channel c on a.channelId=c.id " +
            "where c.id=#{channelId} order by ${sort} ${order}")
    List<ArticleM> getArticleList(int channelId, String sort, String order);

    @Select("select article.id,channelId,channel.name channelName,title,article.img,duration,audio,video,count,article.collection,addTime from article join channel on article.channelId=channel.id where article.id=#{articleId}")
    ArticleDetailM getArticle(int articleId);

    @Select("select id,articleId,`order`,english,translation,node,`first` from sentence where articleId=#{articleId} order by `order`")
    List<Sentence> getSentenceList(int articleId);

    @Insert("insert into ftype(name) values(#{name})")
    boolean addFtype(String name);

    @Insert("insert into stype(ftypeId,name) values(#{ftypeId},#{name})")
    boolean addStype(int ftypeId, String name);

    @Update("update ftype set name=#{name} where id=#{ftypeId}")
    boolean setFtype(int ftypeId, String name);

    @Update("update stype set name=#{name} where id=#{stypeId}")
    boolean setStype(int stypeId, String name);

    @Delete("delete from ftype where id=#{ftypeId}")
    void removeFtype(int ftypeId);

    @Delete("delete from stype where id=#{stypeId}")
    void removeStype(int stypeId);

    @Insert("insert into channel(stypeId,name,info,img) values(#{stypeId},#{name},#{info},#{img})")
    void addChannel(Channel channel);

    @Update("update channel set name=#{name},info=#{info},img=#{img} where id=#{id}")
    void setChannel(Channel channel);

    @Update("update channel set name=#{name},info=#{info} where id=#{id}")
    void setChannelWithNoImg(Channel channel);

    @Select("select img from channel where id=#{id}")
    String getChannelImg(int id);

    @Delete("delete from channel where id=#{id}")
    void removeChannel(int id);

    @Insert("insert into article(channelId,title,duration,img,audio,video) values(#{channelId},#{title},#{duration},#{img},#{audio},#{video})")
    void addArticle(Article article);

    @Update("update article set title=#{title} where id=#{id}")
    void setArticleNoAll(Article article);

    @Update("update article set title=#{title},img=#{img} where id=#{id}")
    void setArticleImg(Article article);

    @Update("update article set title=#{title},audio=#{audio},video=null where id=#{id}")
    void setArticleAudio(Article article);

    @Update("update article set title=#{title},video=#{video},audio=null where id=#{id}")
    void setArticleVideo(Article article);

    @Update("update article set title=#{title},img=#{img},audio=#{audio},video=null where id=#{id}")
    void setArticleImgAudio(Article article);

    @Update("update article set title=#{title},img=#{img},video=#{video},audio=null where id=#{id}")
    void setArticleImgVideo(Article article);

    @Select("select img from article where id=#{id}")
    String getArticleImg(int id);

    @Select("select audio from article where id=#{id}")
    String getArticleAudio(int id);

    @Select("select video from article where id=#{id}")
    String getArticleVideo(int id);

    @Delete("delete from article where id=#{id}")
    void removeArticle(int id);

    @Insert("insert into sentence(articleId,`order`,english,translation,node,first) values(#{articleId},#{order},#{english},#{translation},#{node},#{first})")
    boolean addSentence(Sentence sentence);

    @Select("select max(`order`) from sentence where articleId=#{articleId}")
    Integer getMaxOrder(int articleId);

    @Update("update sentence set `order`=#{order},english=#{english},translation=#{translation},node=#{node},first=#{first} where id=#{id}")
    boolean setSentence(Sentence sentence);

    @Delete("delete from sentence where id=#{id}")
    boolean removeSentence(int id);
}
