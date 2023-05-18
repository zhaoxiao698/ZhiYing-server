package com.zhaoxiao.service;

import com.zhaoxiao.entity.community.ImageViewInfo;
import com.zhaoxiao.entity.community.Topic;
import com.zhaoxiao.entity.community.Trend;
import com.zhaoxiao.mapper.CommunityMapper;
import com.zhaoxiao.model.community.CommentM;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.util.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.accessPath}")
    private String accessPath;

    public List<TrendM> getTrendList(int sort, boolean order, String fanAccount, int topicId, String account) {
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
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
            getImageViewInfoList(trend);
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
            trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
            trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
            trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
            switch (trend.getLinkType()){
                case 1:
                    trend.setLinkTypeS("文章笔记");
                    Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                    if (articleNoteInfo!=null) {
                        if (articleNoteInfo.get("info") != null)
                            trend.setLinkTitle(articleNoteInfo.get("info"));
                        if (articleNoteInfo.get("channelName") != null)
                            trend.setChannelName(articleNoteInfo.get("channelName"));
                        if (articleNoteInfo.get("articleImg") != null)
                            trend.setArticleImg(articleNoteInfo.get("articleImg"));
                    }
                    break;
                case 2:
                    trend.setLinkTypeS("题目笔记");
                    trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setSubType("听力");
                            break;
                        case 2:
                            trend.setSubType("选词填空");
                            break;
                        case 3:
                            trend.setSubType("匹配");
                            break;
                        case 4:
                            trend.setSubType("阅读理解");
                            break;
                        case 5:
                            trend.setSubType("翻译");
                            break;
                        case 6:
                            trend.setSubType("写作");
                            break;
                        case 7:
                            trend.setSubType("完形填空");
                            break;
                        case 8:
                            trend.setSubType("新题型");
                            break;
                    }
                    break;
                case 3:
                    trend.setLinkTypeS("文章");
                    trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                    break;
                case 4:
                    trend.setLinkTypeS("题目");
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                            break;
                        case 2:
                            trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                            break;
                        case 3:
                            trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                            break;
                        case 4:
                            trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                            break;
                        case 5:
                            trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                            break;
                        case 6:
                            trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                            break;
                        case 7:
                            trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                            break;
                        case 8:
                            trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                            break;
                    }
                    break;
                case 5:
                    trend.setLinkTypeS("动态");
                    trend.setLinkTitle(communityMapper.getTrendInfo(trend.getLinkId()));
                    break;
            }
        }

//        for (TrendM trend : trendList) {
//            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
//        }
//
//        for (TrendM trend : trendList) {
//            getImageViewInfoList(trend);
//        }
//
//        for (TrendM trend : trendList) {
//            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
//        }

        return trendList;
    }

    public List<CommentM> getCommentList(int sort, boolean order, int trendId,String account) {
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

        List<CommentM> commentList = communityMapper.getCommentList(sortS, orderS, trendId);
        for (CommentM comment : commentList) {
            comment.setLikeStatus(communityMapper.getCommentLike(account, comment.getId()) != null);
        }
        return commentList;
    }

    public TrendM getTrend(int trendId,String account) {
        TrendM trend = communityMapper.getTrend(trendId);

//        trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
//
//        trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
//
//        getImageViewInfoList(trend);
//
//        trend.setHotComment(communityMapper.getHotComment(trend.getId()));

        trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
        trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
        getImageViewInfoList(trend);
        trend.setHotComment(communityMapper.getHotComment(trend.getId()));
        trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
        trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
        trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
        switch (trend.getLinkType()){
            case 1:
                trend.setLinkTypeS("文章笔记");
                Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                if (articleNoteInfo!=null) {
                    if (articleNoteInfo.get("info") != null)
                        trend.setLinkTitle(articleNoteInfo.get("info"));
                    if (articleNoteInfo.get("channelName") != null)
                        trend.setChannelName(articleNoteInfo.get("channelName"));
                    if (articleNoteInfo.get("articleImg") != null)
                        trend.setArticleImg(articleNoteInfo.get("articleImg"));
                }
                break;
            case 2:
                trend.setLinkTypeS("题目笔记");
                trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                switch (trend.getLinkTable()){
                    case 1:
                        trend.setSubType("听力");
                        break;
                    case 2:
                        trend.setSubType("选词填空");
                        break;
                    case 3:
                        trend.setSubType("匹配");
                        break;
                    case 4:
                        trend.setSubType("阅读理解");
                        break;
                    case 5:
                        trend.setSubType("翻译");
                        break;
                    case 6:
                        trend.setSubType("写作");
                        break;
                    case 7:
                        trend.setSubType("完形填空");
                        break;
                    case 8:
                        trend.setSubType("新题型");
                        break;
                }
                break;
            case 3:
                trend.setLinkTypeS("文章");
                trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                break;
            case 4:
                trend.setLinkTypeS("题目");
                switch (trend.getLinkTable()){
                    case 1:
                        trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                        break;
                    case 2:
                        trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                        break;
                    case 3:
                        trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                        break;
                    case 4:
                        trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                        break;
                    case 5:
                        trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                        break;
                    case 6:
                        trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                        break;
                    case 7:
                        trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                        break;
                    case 8:
                        trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                        break;
                }
                break;
            case 5:
                trend.setLinkTypeS("动态");
                trend.setLinkTitle(communityMapper.getTrendInfo(trend.getLinkId()));
                break;
        }

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

    public Topic getTopic(int topicId,String account) {
        Topic topic = communityMapper.getTopic(topicId);
        topic.setCollectStatus(communityMapper.getTopicCollect(account,topicId)!=null);
        return topic;

    }

    public List<TrendM> getTrendCollectionList(String account) {
        List<TrendM> trendList = communityMapper.getTrendCollectionList(account);

        for (TrendM trend : trendList) {
            trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
            getImageViewInfoList(trend);
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
            trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
            trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
            trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
            switch (trend.getLinkType()){
                case 1:
                    trend.setLinkTypeS("文章笔记");
                    Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                    if (articleNoteInfo!=null) {
                        if (articleNoteInfo.get("info") != null)
                            trend.setLinkTitle(articleNoteInfo.get("info"));
                        if (articleNoteInfo.get("channelName") != null)
                            trend.setChannelName(articleNoteInfo.get("channelName"));
                        if (articleNoteInfo.get("articleImg") != null)
                            trend.setArticleImg(articleNoteInfo.get("articleImg"));
                    }
                    break;
                case 2:
                    trend.setLinkTypeS("题目笔记");
                    trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setSubType("听力");
                            break;
                        case 2:
                            trend.setSubType("选词填空");
                            break;
                        case 3:
                            trend.setSubType("匹配");
                            break;
                        case 4:
                            trend.setSubType("阅读理解");
                            break;
                        case 5:
                            trend.setSubType("翻译");
                            break;
                        case 6:
                            trend.setSubType("写作");
                            break;
                        case 7:
                            trend.setSubType("完形填空");
                            break;
                        case 8:
                            trend.setSubType("新题型");
                            break;
                    }
                    break;
                case 3:
                    trend.setLinkTypeS("文章");
                    trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                    break;
                case 4:
                    trend.setLinkTypeS("题目");
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                            break;
                        case 2:
                            trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                            break;
                        case 3:
                            trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                            break;
                        case 4:
                            trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                            break;
                        case 5:
                            trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                            break;
                        case 6:
                            trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                            break;
                        case 7:
                            trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                            break;
                        case 8:
                            trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                            break;
                    }
                    break;
            }
        }

//        for (TrendM trend : trendList) {
//            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
//        }
//
//        for (TrendM trend : trendList) {
//            getImageViewInfoList(trend);
//        }
//
//        for (TrendM trend : trendList) {
//            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
//        }

        return trendList;
    }

    public List<Topic> getTopicCollectionList(String account) {
        return communityMapper.getTopicCollectionList(account);
    }

    public List<TrendM> getTrendHistoryList(String account) {
        List<TrendM> trendList = communityMapper.getTrendHistoryList(account);

        for (TrendM trend : trendList) {
            trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
            getImageViewInfoList(trend);
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
            trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
            trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
            trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
            switch (trend.getLinkType()){
                case 1:
                    trend.setLinkTypeS("文章笔记");
                    Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                    if (articleNoteInfo!=null) {
                        if (articleNoteInfo.get("info") != null)
                            trend.setLinkTitle(articleNoteInfo.get("info"));
                        if (articleNoteInfo.get("channelName") != null)
                            trend.setChannelName(articleNoteInfo.get("channelName"));
                        if (articleNoteInfo.get("articleImg") != null)
                            trend.setArticleImg(articleNoteInfo.get("articleImg"));
                    }
                    break;
                case 2:
                    trend.setLinkTypeS("题目笔记");
                    trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setSubType("听力");
                            break;
                        case 2:
                            trend.setSubType("选词填空");
                            break;
                        case 3:
                            trend.setSubType("匹配");
                            break;
                        case 4:
                            trend.setSubType("阅读理解");
                            break;
                        case 5:
                            trend.setSubType("翻译");
                            break;
                        case 6:
                            trend.setSubType("写作");
                            break;
                        case 7:
                            trend.setSubType("完形填空");
                            break;
                        case 8:
                            trend.setSubType("新题型");
                            break;
                    }
                    break;
                case 3:
                    trend.setLinkTypeS("文章");
                    trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                    break;
                case 4:
                    trend.setLinkTypeS("题目");
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                            break;
                        case 2:
                            trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                            break;
                        case 3:
                            trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                            break;
                        case 4:
                            trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                            break;
                        case 5:
                            trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                            break;
                        case 6:
                            trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                            break;
                        case 7:
                            trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                            break;
                        case 8:
                            trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                            break;
                    }
                    break;
            }
        }

//        for (TrendM trend : trendList) {
//            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
//        }
//
//        for (TrendM trend : trendList) {
//            getImageViewInfoList(trend);
//        }
//
//        for (TrendM trend : trendList) {
//            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
//        }

        return trendList;
    }

    public boolean addTrendRecord(String account, int trendId) {
        if (communityMapper.getTrendRecord(account, trendId)==null){
            communityMapper.addTrendRecord(account,trendId);
            return true;
        } else {
            communityMapper.setTrendRecord(account,trendId,new Date());
            return true;
        }
    }

    public boolean publishTrend(String account,
                                String title,
                                String info,
                                List<Integer> topicIdList,
                                List<MultipartFile> imgFileList,
                                int linkId,
                                int linkType,
                                int linkTable){
//        System.out.println(account);
//        System.out.println(title);
//        System.out.println(info);
//        System.out.println(topicIdList);
//        System.out.println(imgList);

//        int id = 0;
//        int a = communityMapper.addTrend(id, account, title, info, linkId, linkType, linkTable);
//        System.out.println("自增id："+id);
//        System.out.println("a："+a);

        Trend trend = new Trend();
        System.out.println("trendId:"+trend.getId());
        trend.setUserAccount(account);
        trend.setTitle(title);
        trend.setInfo(info);
        trend.setLinkId(linkId);
        trend.setLinkType(linkType);
        trend.setLinkTable(linkTable);
        communityMapper.addTrend(trend);
        System.out.println("trendId:"+trend.getId());

        for (Integer topicId : topicIdList) {
            communityMapper.addTopic(trend.getId(),topicId);
        }

        for (MultipartFile imgFile : imgFileList) {
            try {
                // 尝试读取上传的文件为图片
                BufferedImage image = ImageIO.read(imgFile.getInputStream());
                if (image != null) {
                    String img = addFile(imgFile,"trend/img");
                    communityMapper.addImg(trend.getId(),img);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //动态分享数
        if (trend.getLinkType()==5){
            communityMapper.addTrendShareNum(trend.getLinkId());
        }

        return true;
    }

    private String addFile(MultipartFile imgFile, String folder) {
        String channelPath = uploadFolder + folder;

        File file = new File(channelPath);
        if(!file.exists()){
            MyFile.mkDirectory(channelPath);
        }
        String oldName = imgFile.getOriginalFilename();
        String newName;
        String img = "";
        if (oldName!=null&&!oldName.equals("")) {
            newName= UUID.randomUUID().toString().replace("-","")
                    +oldName.substring(oldName.lastIndexOf("."));
        } else {
            newName= UUID.randomUUID().toString().replace("-","");
        }
        try {
            imgFile.transferTo(new File(file, Objects.requireNonNull(newName)));
            img = accessPath + folder + "/" + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public boolean deleteTrend(int trendId) {
        //删除原来的文件
        List<String> oldImgList = communityMapper.getImgList(trendId);
        for (String oldImg : oldImgList) {
            if (oldImg!=null){
                removeImg(oldImg);
            }
        }
        communityMapper.removeImg(trendId);
        communityMapper.removeTopicOf(trendId);
        communityMapper.removeTrend(trendId);
        return true;
    }

    private void removeImg(String oldImg) {
        String replacedPath = oldImg.replaceFirst(accessPath,uploadFolder);
        File fileToDelete = new File(replacedPath);
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The specified file does not exist.");
        }
    }

    public boolean createTopic(Topic topic) {
        if (topic.getName()==null||topic.getName().equals("")){
            topic.setName("未知");
        }
//        if (communityMapper.getTopicByName(topic.getName())!=null&&communityMapper.getTopicByName(topic.getName()).size()!=0){
//            return false;
//        }
        return communityMapper.createTopic(topic);
    }

    public boolean like(String account, int trendId, boolean like) {
        if(like){
            if(communityMapper.getLike(account,trendId)==null) {
                communityMapper.addTrendLikeNum(trendId);
                return communityMapper.addLike(account, trendId);
            } else {
                return true;
            }
        } else {
            if(communityMapper.getLike(account,trendId)!=null) {
                communityMapper.subTrendLikeNum(trendId);
                return communityMapper.removeLike(account, trendId);
            } else {
                return true;
            }
        }
    }

    public boolean collect(String account, int trendId, boolean collect) {
        if(collect){
            if(communityMapper.getCollect(account,trendId)==null) {
                communityMapper.addTrendCollectionNum(trendId);
                return communityMapper.addCollect(account, trendId);
            } else {
                return true;
            }
        } else {
            if(communityMapper.getCollect(account,trendId)!=null) {
                communityMapper.subTrendCollectionNum(trendId);
                return communityMapper.removeCollect(account, trendId);
            } else {
                return true;
            }
        }
    }

    public boolean attention(String userAccount, String fanAccount, boolean attention) {
        if(attention){
            if(communityMapper.getAttention(userAccount,fanAccount)==null) {
                return communityMapper.addAttention(userAccount, fanAccount);
            } else {
                return true;
            }
        } else {
            if(communityMapper.getAttention(userAccount,fanAccount)!=null) {
                return communityMapper.removeAttention(userAccount, fanAccount);
            } else {
                return true;
            }
        }
    }

    public boolean topicCollect(String account, int topicId, boolean collect) {
        if(collect){
            if(communityMapper.getTopicCollect(account,topicId)==null) {
                communityMapper.addTopicCollectionNum(topicId);
                return communityMapper.addTopicCollect(account, topicId);
            } else {
                return true;
            }
        } else {
            if(communityMapper.getTopicCollect(account,topicId)!=null) {
                communityMapper.subTopicCollectionNum(topicId);
                return communityMapper.removeTopicCollect(account, topicId);
            } else {
                return true;
            }
        }
    }

    public List<TrendM> getTrendSearchList(String searchWord, String account) {
        List<TrendM> trendList;
        trendList = communityMapper.getTrendSearchList(searchWord);
        for (TrendM trend : trendList) {
            trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
            getImageViewInfoList(trend);
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
            trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
            trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
            trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
            switch (trend.getLinkType()){
                case 1:
                    trend.setLinkTypeS("文章笔记");
                    Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                    if (articleNoteInfo!=null) {
                        if (articleNoteInfo.get("info") != null)
                            trend.setLinkTitle(articleNoteInfo.get("info"));
                        if (articleNoteInfo.get("channelName") != null)
                            trend.setChannelName(articleNoteInfo.get("channelName"));
                        if (articleNoteInfo.get("articleImg") != null)
                            trend.setArticleImg(articleNoteInfo.get("articleImg"));
                    }
                    break;
                case 2:
                    trend.setLinkTypeS("题目笔记");
                    trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setSubType("听力");
                            break;
                        case 2:
                            trend.setSubType("选词填空");
                            break;
                        case 3:
                            trend.setSubType("匹配");
                            break;
                        case 4:
                            trend.setSubType("阅读理解");
                            break;
                        case 5:
                            trend.setSubType("翻译");
                            break;
                        case 6:
                            trend.setSubType("写作");
                            break;
                        case 7:
                            trend.setSubType("完形填空");
                            break;
                        case 8:
                            trend.setSubType("新题型");
                            break;
                    }
                    break;
                case 3:
                    trend.setLinkTypeS("文章");
                    trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                    break;
                case 4:
                    trend.setLinkTypeS("题目");
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                            break;
                        case 2:
                            trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                            break;
                        case 3:
                            trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                            break;
                        case 4:
                            trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                            break;
                        case 5:
                            trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                            break;
                        case 6:
                            trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                            break;
                        case 7:
                            trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                            break;
                        case 8:
                            trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                            break;
                    }
                    break;
                case 5:
                    trend.setLinkTypeS("动态");
                    trend.setLinkTitle(communityMapper.getTrendInfo(trend.getLinkId()));
                    break;
            }
        }
        return trendList;
    }

    public boolean sendComment(int trendId, String account, String info) {
        return communityMapper.sendComment(trendId,account,info);
    }

    public boolean likeComment(int commentId, String account, boolean like) {
        if(like){
            if(communityMapper.getCommentLike(account,commentId)==null) {
                communityMapper.addCommentLikeNum(commentId);
                return communityMapper.addCommentLike(account, commentId);
            } else {
                return true;
            }
        } else {
            if(communityMapper.getCommentLike(account,commentId)!=null) {
                communityMapper.subCommentLikeNum(commentId);
                return communityMapper.removeCommentLike(account, commentId);
            } else {
                return true;
            }
        }
    }

    public List<Topic> getTopicSearchList(String searchWord) {
        return communityMapper.getTopicSearchList(searchWord);
    }
}
