package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.entity.community.Trend;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.model.test.*;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Mapper
public interface CommunityMapper {
    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share,linkId,linkType,linkTable from trend join user on user.account=trend.userAccount ${sort} ${order}")
    List<TrendM> getTrendList(String sort, String order);

    @Select("select id,trend.userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share,linkId,linkTable,linkType from attention join trend on attention.userAccount=trend.userAccount join user on user.account=trend.userAccount where fanAccount=#{fanAccount} ${sort} ${order}")
    List<TrendM> getAttentionTrendList(String fanAccount, String sort, String order);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share,linkId,linkType,linkTable from trend join topicOf on topicOf.trendId=trend.id join user on user.account=trend.userAccount where topicId=#{topicId} ${sort} ${order}")
    List<TrendM> getTopicTrendList(String sort, String order, int topicId);

    @Select("select id,name from topic join topicOf on topic.id = topicOf.topicId where trendId = #{trendId}")
    List<Topic> getTopicListOfTrend(int trendId);

    @Select("select account,name from user join remindOf on user.account = remindOf.userAccount where trendId = #{trendId}")
    List<User> getUserListOfTrend(int trendId);

    @Select("select img from img where trendId = #{trendId}")
    List<String> getImgList(int trendId);

    @Select("select id,trendId,userAccount,name userName,avatar userAvatar,info,comment.addTime,`like` from comment join user on comment.userAccount=user.account where trendId = #{trendId} order by `like` desc limit 1")
    CommentM getHotComment(int trendId);

    @Select("select id,trendId,userAccount,name userName,avatar userAvatar,info,comment.addTime,`like` from comment join user on user.account=comment.userAccount where trendId = #{trendId} ${sort} ${order}")
    List<CommentM> getCommentList(String sort, String order, int trendId);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share,linkId,linkType,linkType from trend join user on user.account=trend.userAccount where id=#{trendId}")
    TrendM getTrend(int trendId);

    @Select("select * from topic order by `join` desc")
    List<Topic> getTopicList();

    @Select("select * from topic where id=#{topicId}")
    Topic getTopic(int topicId);

    @Select("select id,t.userAccount,name userName,avatar userAvatar,title,info,t.addTime,`like`,collection,comment,share,linkId,linkType,linkTable from " +
            "trend t join user u on u.account=t.userAccount join trendCollection tc on t.id=tc.trendId where tc.userAccount=#{account} order by tc.addTime desc")
    List<TrendM> getTrendCollectionList(String account);

    @Select("select * from topic t join topicCollection tc on t.id=tc.topicId where tc.userAccount=#{account} order by tc.addTime desc")
    List<Topic> getTopicCollectionList(String account);

    @Select("select id,t.userAccount,name userName,avatar userAvatar,title,info,t.addTime,`like`,collection,comment,share,tc.addTime historyTime,linkId,linkType,linkTable from " +
            "trend t join user u on u.account=t.userAccount join trendRecord tc on t.id=tc.trendId where tc.userAccount=#{account} order by tc.addTime desc")
    List<TrendM> getTrendHistoryList(String account);

    @Select("select userAccount from trendRecord where userAccount=#{account} and trendId=#{trendId}")
    String getTrendRecord(String account, int trendId);

    @Insert("insert into trendRecord(userAccount,trendId) values(#{account},#{trendId})")
    void addTrendRecord(String account, int trendId);

    @Update("update trendRecord set addTime=#{addTime} where userAccount=#{account} and trendId=#{trendId}")
    void setTrendRecord(String account, int trendId, Date addTime);

    @Insert("insert into trend(userAccount,title,info,linkId,linkType,linkTable) values(#{userAccount},#{title},#{info},#{linkId},#{linkType},#{linkTable})")
//    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    @Options(useGeneratedKeys=true, keyProperty="id")
    void addTrend(Trend trend);

    @Insert("insert into topicOf(trendId,topicId) values(#{trendId},#{topicId})")
    void addTopic(int trendId,int topicId);

    @Insert("insert into img(trendId,img) values(#{trendId},#{img})")
    void addImg(int trendId,String img);

    @Delete("delete from img where trendId=#{trendId}")
    void removeImg(int trendId);

    @Delete("delete from topicOf where trendId=#{trendId}")
    void removeTopicOf(int trendId);

    @Delete("delete from trend where id=#{id}")
    void removeTrend(int trendId);

    @Select("select an.info,c.name channelName,a.img articleImg from articleNote an join article a on an.articleId=a.id join channel c on a.channelId=c.id where an.userAccount=#{account} and an.articleId=#{articleId}")
    Map<String,String> getArticleNoteInfo(String account, int articleId);

    @Select("select info from testNote where userAccount=#{account} and questionId=#{questionId} and `table`=#{table}")
    String getTestNoteInfo(String account, int questionId, int table);

    @Select("select info from article where id=#{id}")
    String getArticleTitle(int id);

    @Select("select info from listening where id=#{id}")
    String getListeningInfo(int id);

    @Select("select info from banked where id=#{id}")
    String getBankedListInfo(int id);

    @Select("select info from `match` where id=#{id}")
    String getMatchListInfo(int id);

    @Select("select info from careful where id=#{id}")
    String getCarefulListInfo(int id);

    @Select("select info from translation where id=#{id}")
    String getTranslationListInfo(int id);

    @Select("select info from writing where id=#{id}")
    String getWritingListInfo(int id);

    @Select("select info from cloze where id=#{id}")
    String getClozeListInfo(int id);

    @Select("select info from new where id=#{id}")
    String getNewListInfo(int id);

    @Insert("insert into topic(name,info,userAccount) values(#{name},#{info},#{userAccount})")
    boolean createTopic(Topic topic);

    @Select("select 1 from topic where name=#{name}")
    List<Integer> getTopicByName(String name);

    @Select("select 1 from trendLike where userAccount=#{account} and trendId=#{trendId}")
    Integer getLike(String account, int trendId);

    @Insert("insert into trendLike(userAccount,trendId) values(#{account},#{trendId})")
    boolean addLike(String account, int trendId);

    @Delete("delete from trendLike where userAccount=#{account} and trendId=#{trendId}")
    boolean removeLike(String account, int trendId);

    @Select("select 1 from trendCollection where userAccount=#{account} and trendId=#{trendId}")
    Integer getCollect(String account, int trendId);

    @Insert("insert into trendCollection(userAccount,trendId) values(#{account},#{trendId})")
    boolean addCollect(String account, int trendId);

    @Delete("delete from trendCollection where userAccount=#{account} and trendId=#{trendId}")
    boolean removeCollect(String account, int trendId);

    @Select("select info from trend where id=#{id}")
    String getTrendInfo(int id);

    @Select("select 1 from attention where userAccount=#{userAccount} and fanAccount=#{fanAccount}")
    Integer getAttention(String userAccount, String fanAccount);

    @Insert("insert into attention(userAccount,fanAccount) values(#{userAccount},#{fanAccount})")
    boolean addAttention(String userAccount, String fanAccount);

    @Delete("delete from attention where userAccount=#{userAccount} and fanAccount=#{fanAccount}")
    boolean removeAttention(String userAccount, String fanAccount);

    @Select("select 1 from topicCollection where userAccount=#{account} and topicId=#{topicId}")
    Integer getTopicCollect(String account, int topicId);

    @Insert("insert into topicCollection(userAccount,topicId) values(#{account},#{topicId})")
    boolean addTopicCollect(String account, int topicId);

    @Delete("delete from topicCollection where userAccount=#{account} and topicId=#{topicId}")
    boolean removeTopicCollect(String account, int topicId);

    @Update("update trend set collection=collection+1 where id=#{trendId}")
    void addTrendCollectionNum(int trendId);

    @Update("update trend set collection=collection-1 where id=#{trendId}")
    void subTrendCollectionNum(int trendId);

    @Update("update topic set collection=collection+1 where id=#{topicId}")
    void addTopicCollectionNum(int topicId);

    @Update("update topic set collection=collection-1 where id=#{topicId}")
    void subTopicCollectionNum(int topicId);

    @Update("update trend set share=share+1 where id=#{linkId}")
    void addTrendShareNum(int linkId);
}
