package com.zhaoxiao.service.admin;

import com.zhaoxiao.entity.community.ImageViewInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.mapper.admin.AdminCommunityMapper;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminCommunityService {
    @Autowired
    private AdminCommunityMapper adminCommunityMapper;

    public List<TrendM> getTrendList() {
        List<TrendM> trendList = adminCommunityMapper.getTrendList();

        for (TrendM trend : trendList) {
            trend.setTopicList(adminCommunityMapper.getTopicListOfTrend(trend.getId()));
        }

        for (TrendM trend : trendList) {
            trend.setUserList(adminCommunityMapper.getUserListOfTrend(trend.getId()));
        }

        for (TrendM trend : trendList) {
            getImageViewInfoList(trend);
        }

        return trendList;
    }

    public List<CommentM> getCommentList(int trendId) {
        return adminCommunityMapper.getCommentList(trendId);
    }

    public TrendM getTrend(int trendId) {
        TrendM trend = adminCommunityMapper.getTrend(trendId);

        trend.setTopicList(adminCommunityMapper.getTopicListOfTrend(trend.getId()));

        trend.setUserList(adminCommunityMapper.getUserListOfTrend(trend.getId()));

        getImageViewInfoList(trend);

        return trend;
    }

    private void getImageViewInfoList(TrendM trend) {
        List<ImageViewInfo> imgList = new ArrayList<>();
        List<String> imgs = adminCommunityMapper.getImgList(trend.getId());
        for (String img : imgs) {
            ImageViewInfo info = new ImageViewInfo(img);
            imgList.add(info);
        }
        trend.setImgList(imgList);
    }

    public List<Topic> getTopicList() {
        return adminCommunityMapper.getTopicList();
    }

    public Topic getTopic(int topicId) {
        return adminCommunityMapper.getTopic(topicId);
    }

    public boolean removeTrend(int id) {
        return adminCommunityMapper.removeTrend(id);
    }

    public boolean removeTopic(int id) {
        return adminCommunityMapper.removeTopic(id);
    }
}
