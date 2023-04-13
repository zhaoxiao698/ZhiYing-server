package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommunityMapper {
    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from trend join user on user.account=trend.userAccount ${sort} ${order}")
    List<TrendM> getTrendList(String sort, String order);

    @Select("select id,trend.userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from attention join trend on attention.userAccount=trend.userAccount join user on user.account=trend.userAccount where fanAccount=#{fanAccount} ${sort} ${order}")
    List<TrendM> getAttentionTrendList(String fanAccount, String sort, String order);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from trend join topicof on topicof.trendId=trend.id join user on user.account=trend.userAccount where topicId=#{topicId} ${sort} ${order}")
    List<TrendM> getTopicTrendList(String sort, String order, int topicId);

    @Select("select id,name from topic join topicof on topic.id = topicof.topicId where trendId = #{trendId}")
    List<Topic> getTopicListOfTrend(int trendId);

    @Select("select account,name from user join remindof on user.account = remindof.userAccount where trendId = #{trendId}")
    List<User> getUserListOfTrend(int trendId);

    @Select("select img from img where trendId = #{trendId}")
    List<String> getImgList(int trendId);

    @Select("select id,trendId,userAccount,name userName,avatar userAvatar,info,comment.addTime,`like` from comment join user on comment.userAccount=user.account where trendId = #{trendId} order by `like` desc limit 1")
    CommentM getHotComment(int trendId);

    @Select("select id,trendId,userAccount,name userName,avatar userAvatar,info,comment.addTime,`like` from comment join user on user.account=comment.userAccount where trendId = #{trendId} ${sort} ${order}")
    List<CommentM> getCommentList(String sort, String order, int trendId);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from trend join user on user.account=trend.userAccount where id=#{trendId}")
    TrendM getTrend(int trendId);

    @Select("select * from topic order by `join` desc")
    List<Topic> getTopicList();

    @Select("select * from topic where id=#{topicId}")
    Topic getTopic(int topicId);
}