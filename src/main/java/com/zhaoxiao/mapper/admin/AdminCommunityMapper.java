package com.zhaoxiao.mapper.admin;

import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminCommunityMapper {
    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from trend join user on user.account=trend.userAccount order by addTime desc")
    List<TrendM> getTrendList();

    @Select("select id,name from topic join topicOf on topic.id = topicOf.topicId where trendId = #{trendId}")
    List<Topic> getTopicListOfTrend(int trendId);

    @Select("select account,name from user join remindOf on user.account = remindOf.userAccount where trendId = #{trendId}")
    List<User> getUserListOfTrend(int trendId);

    @Select("select img from img where trendId = #{trendId}")
    List<String> getImgList(int trendId);

    @Select("select id,trendId,userAccount,name userName,avatar userAvatar,info,comment.addTime,`like` from comment join user on user.account=comment.userAccount where trendId = #{trendId} order by addTime desc")
    List<CommentM> getCommentList(int trendId);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share from trend join user on user.account=trend.userAccount where id=#{trendId}")
    TrendM getTrend(int trendId);

    @Select("select * from topic order by `join` desc")
    List<Topic> getTopicList();

    @Select("select * from topic where id=#{topicId}")
    Topic getTopic(int topicId);

    @Delete("delete from trend where id=#{id}")
    boolean removeTrend(int id);

    @Delete("delete from topic where id=#{id}")
    boolean removeTopic(int id);
}
