package com.zhaoxiao.service;

import com.zhaoxiao.entity.community.ImageViewInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.mapper.CommunityMapper;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    public List<TrendM> getTrendList(int sort, boolean order, String fanAccount, int topicId) {
        List<TrendM> trendList;

        String sortS = "";
        switch (sort) {
            case 0:
                sortS = "order by addTime";
                break;
            case 1:
                sortS = "order by `like`";
                break;
            case 2:
                sortS = "order by collection";
                break;
        }

        String orderS;
        if (order) orderS = "asc";
        else orderS = "desc";

        if (fanAccount != null && !fanAccount.equals("")) {
            trendList = communityMapper.getAttentionTrendList(fanAccount, sortS, orderS);
        } else {
            if (topicId>0){
                trendList = communityMapper.getTopicTrendList(sortS, orderS,topicId);
            } else {
                trendList = communityMapper.getTrendList(sortS, orderS);
            }
        }

        for (TrendM trend : trendList) {
            trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
        }

        for (TrendM trend : trendList) {
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
        }

        for (TrendM trend : trendList) {
            getImageViewInfoList(trend);
        }

        for (TrendM trend : trendList) {
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
        }

        return trendList;
    }

    public List<CommentM> getCommentList(int sort, boolean order, int trendId) {
        String sortS = "";
        switch (sort) {
            case 0:
                sortS = "order by addTime";
                break;
            case 1:
                sortS = "order by `like`";
                break;
        }

        String orderS;
        if (order) orderS = "asc";
        else orderS = "desc";

        return communityMapper.getCommentList(sortS, orderS, trendId);
    }

    public TrendM getTrend(int trendId) {
        TrendM trend = communityMapper.getTrend(trendId);

        trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));

        trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));

        getImageViewInfoList(trend);

        trend.setHotComment(communityMapper.getHotComment(trend.getId()));

        return trend;
    }

    private void getImageViewInfoList(TrendM trend) {
        List<ImageViewInfo> imgList = new ArrayList<>();
        List<String> imgs = communityMapper.getImgList(trend.getId());
        for (String img : imgs) {
            ImageViewInfo info = new ImageViewInfo(img);
            imgList.add(info);
        }
        trend.setImgList(imgList);
    }

    public List<Topic> getTopicList() {
        return communityMapper.getTopicList();
    }

    public Topic getTopic(int topicId) {
        return communityMapper.getTopic(topicId);
    }
}
