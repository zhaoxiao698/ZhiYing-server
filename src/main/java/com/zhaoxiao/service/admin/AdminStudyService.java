package com.zhaoxiao.service.admin;

import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.study.Article;
import com.zhaoxiao.entity.study.Channel;
import com.zhaoxiao.entity.study.Sentence;
import com.zhaoxiao.mapper.admin.AdminStudyMapper;
import com.zhaoxiao.model.study.ArticleDetailM;
import com.zhaoxiao.model.study.ArticleM;
import com.zhaoxiao.model.study.ChannelM;
import com.zhaoxiao.model.study.Ftype;
import com.zhaoxiao.util.FileUploadUtil;
import com.zhaoxiao.util.MyFile;
import it.sauronsoftware.jave.EncoderException;
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
public class AdminStudyService {
    @Autowired
    private AdminStudyMapper adminStudyMapper;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.accessPath}")
    private String accessPath;

    public List<Ftype> getTypeList() {
        List<Ftype> ftypeList = adminStudyMapper.getFtypeList();
        for (Ftype ftype : ftypeList) {
            ftype.setStypeList(adminStudyMapper.getStypeList(ftype.getId()));
        }
        return ftypeList;
    }

    public Ftype getFtypeById(int ftypeId) {
        Ftype ftype = adminStudyMapper.getFtypeById(ftypeId);
        ftype.setStypeList(adminStudyMapper.getStypeList(ftypeId));
        return ftype;
    }

    public List<ChannelM> getChannelList(/*int ftypeId, */int stypeId) {
/*        if(stypeId==0){
            return adminStudyMapper.getChannelListAll(ftypeId);
        }*/
        return adminStudyMapper.getChannelList(stypeId);
    }

    public ChannelM getChannelById(int channelId) {
        return adminStudyMapper.getChannelById(channelId);
    }
    public List<ArticleM> getArticleList(int channelId/*, boolean sort, boolean order*/) {
        //sort和order为boolean时
//        if(sort){
//            if(order){
//                return adminStudyMapper.getArticleList(channelId,"title","asc");
//            }
//            return adminStudyMapper.getArticleList(channelId,"title","desc");
//        }
//        if(order){
//            return adminStudyMapper.getArticleList(channelId,"addTime","asc");
//        }
        return adminStudyMapper.getArticleList(channelId,"addTime","desc");
    }

    public ArticleDetailM getArticleDetail(int articleId) {
        ArticleDetailM articleDetailM = adminStudyMapper.getArticle(articleId);
        articleDetailM.setSentenceList(adminStudyMapper.getSentenceList(articleId));
        return articleDetailM;
    }

    public boolean addFtype(String name) {
        return adminStudyMapper.addFtype(name);
    }

    public boolean addStype(int ftypeId, String name) {
        return adminStudyMapper.addStype(ftypeId,name);
    }

    public boolean setFtype(int ftypeId, String name) {
        return adminStudyMapper.setFtype(ftypeId,name);
    }

    public boolean setStype(int stypeId, String name) {
        return adminStudyMapper.setStype(stypeId,name);
    }

    public Map<String,Object> removeFtype(int ftypeId) {
        Map<String,Object> map = new HashMap<>();
//        List<ChannelM> channelList = getChannelList(ftypeId, 0);
        List<ChannelM> channelList = adminStudyMapper.getChannelListAll(ftypeId);
        if (channelList==null||channelList.size()==0){
            adminStudyMapper.removeFtype(ftypeId);
            map.put("removeFtype",true);
            map.put("message","移除成功");
        } else {
            map.put("removeFtype",false);
            map.put("message","改分类下还有频道，不能删除");
        }
        return map;
    }

    public Map<String,Object> removeStype(int stypeId) {
        Map<String,Object> map = new HashMap<>();
//        List<ChannelM> channelList = getChannelList(0, stypeId);
        List<ChannelM> channelList = adminStudyMapper.getChannelList(stypeId);
        if (channelList==null||channelList.size()==0){
            adminStudyMapper.removeStype(stypeId);
            map.put("removeFtype",true);
            map.put("message","移除成功");
        } else {
            map.put("removeFtype",false);
            map.put("message","改分类下还有频道，不能删除");
        }
        return map;
    }

    public boolean addChannel(int stypeId, String name, String info, MultipartFile imgFile) {
        String img = addFile(imgFile,"channel/img");

        Channel channel = new Channel();
        channel.setStypeId(stypeId);
        channel.setName(name);
        channel.setInfo(info);
        channel.setImg(img);
        adminStudyMapper.addChannel(channel);
        return true;
    }

    public boolean setChannel(int channelId, String name, String info, MultipartFile imgFile) {
        String img = addFile(imgFile,"channel/img");

        //删除原来的文件
        String oldImg = adminStudyMapper.getChannelImg(channelId);
        if (oldImg!=null){
            removeImg(oldImg);
        }

        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName(name);
        channel.setInfo(info);
        channel.setImg(img);
        adminStudyMapper.setChannel(channel);
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

    public boolean setChannelWithNoImg(int channelId, String name, String info) {
        Channel channel = new Channel();
        channel.setId(channelId);
        channel.setName(name);
        channel.setInfo(info);
        adminStudyMapper.setChannelWithNoImg(channel);
        return true;
    }

    public boolean removeChannel(int channelId) {
        //删除原来的文件
        String oldImg = adminStudyMapper.getChannelImg(channelId);
        if (oldImg!=null){
            removeImg(oldImg);
        }

        adminStudyMapper.removeChannel(channelId);
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

    public boolean addArticleVideo(int channelId, String title, MultipartFile imgFile, MultipartFile mediaFile) {
        String img = addFile(imgFile,"article/img");
        String media = addFile(mediaFile,"article/video");

        Article article = new Article();
        article.setChannelId(channelId);
        article.setTitle(title);
        try {
            article.setDuration(getMediaDuration(mediaFile));
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        article.setImg(img);
        article.setVideo(media);
        adminStudyMapper.addArticle(article);
        return true;
    }

    public boolean addArticleAudio(int channelId, String title, MultipartFile imgFile, MultipartFile mediaFile) {
        String img = addFile(imgFile,"article/img");
        String media = addFile(mediaFile,"article/audio");

        Article article = new Article();
        article.setChannelId(channelId);
        article.setTitle(title);
        try {
            article.setDuration(getMediaDuration(mediaFile));
        } catch (EncoderException e) {
            e.printStackTrace();
        }
        article.setImg(img);
        article.setAudio(media);
        adminStudyMapper.addArticle(article);
        return true;
    }

    public String getMediaDuration(MultipartFile mediFile) throws EncoderException{

//        MultimediaInfo info = new MultimediaObject(videoFile.getInputStream(), null).getInfo();
//        long duration = info.getDuration();
//        return (int) (duration / 1000);

//        CommonsMultipartFile cf= (CommonsMultipartFile)mediFile;
//        DiskFileItem fi = (DiskFileItem)cf.getFileItem();
//        File f = fi.getStoreLocation();
//        // 获取视频时长
//        Encoder encoder = new Encoder();
//        MultimediaInfo m = encoder.getInfo(f);
//        long ls = m.getDuration()/1000;
//        int hour = (int) (ls/3600);
//        int minute = (int) (ls%3600)/60;
//        int second = (int) (ls-hour*3600-minute*60);
//        //logger.info("视频时长为：{}时{}分{}秒", hour, minute, second);
//        String time = hour+":"+minute+":"+second;
//        return time;

        long duration = FileUploadUtil.getVideoDuration(mediFile);
//        duration = 100000;
        long minutes = duration / 60;
        long seconds = duration % 60;
        String timeString = String.format("%02d:%02d", minutes, seconds);
        System.out.println(timeString); // 输出 02:03
        return String.valueOf(timeString);
    }

    public boolean setArticle(int articleId, String title, MultipartFile imgFile, MultipartFile mediaFile) {
        if (imgFile!=null&&!imgFile.isEmpty()){
            try {
                // 尝试读取上传的文件为图片
                BufferedImage image = ImageIO.read(imgFile.getInputStream());
                if (image == null) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(mediaFile!=null&&!mediaFile.isEmpty()){
            String fileName = mediaFile.getOriginalFilename();
            String extension = "";
            if (fileName != null) {
                int lastDotIndex = fileName.lastIndexOf(".");
                if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                    extension = fileName.substring(lastDotIndex + 1);
                }
            }
            if (!"mp4".equalsIgnoreCase(extension)&&!"avi".equalsIgnoreCase(extension)&&!"mov".equalsIgnoreCase(extension)&&
                    !"mp3".equalsIgnoreCase(extension)&&!"wav".equalsIgnoreCase(extension)){
                return false;
            }
        }

        Article article = new Article();
        article.setId(articleId);
        article.setTitle(title);
        boolean imgMapper = false;
        boolean mediaMapper = false;
        boolean videoMapper = false;

        if (imgFile==null||imgFile.isEmpty()){

        } else {
            String oldImg = adminStudyMapper.getArticleImg(articleId);
            removeImg(oldImg);
            String img = addFile(imgFile,"article/img");
            article.setImg(img);
            imgMapper=true;
        }

        if (mediaFile==null||mediaFile.isEmpty()){

        } else {
            String fileName = mediaFile.getOriginalFilename();
            String extension = "";
            if (fileName != null) {
                int lastDotIndex = fileName.lastIndexOf(".");
                if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                    extension = fileName.substring(lastDotIndex + 1);
                }
            }

            if ("mp4".equalsIgnoreCase(extension) || "avi".equalsIgnoreCase(extension) || "mov".equalsIgnoreCase(extension)) {
                String oldVideo = adminStudyMapper.getArticleVideo(articleId);
                if (oldVideo!=null){
                    removeImg(oldVideo);
                }
                String oldAudio = adminStudyMapper.getArticleAudio(articleId);
                if (oldAudio!=null){
                    removeImg(oldAudio);
                }
                String video = addFile(mediaFile,"article/video");
                try {
                    article.setDuration(getMediaDuration(mediaFile));
                } catch (EncoderException e) {
                    e.printStackTrace();
                }
                article.setVideo(video);
                mediaMapper=true;
                videoMapper=true;
            } else if ("mp3".equalsIgnoreCase(extension) || "wav".equalsIgnoreCase(extension)) {
                String oldAudio = adminStudyMapper.getArticleAudio(articleId);
                if (oldAudio!=null){
                    removeImg(oldAudio);
                }
                String oldVideo = adminStudyMapper.getArticleVideo(articleId);
                if (oldVideo!=null){
                    removeImg(oldVideo);
                }
                String audio = addFile(mediaFile,"article/audio");
                try {
                    article.setDuration(getMediaDuration(mediaFile));
                } catch (EncoderException e) {
                    e.printStackTrace();
                }
                article.setAudio(audio);
                mediaMapper=true;
            } else {
            }
        }

        if (imgMapper){
            if(mediaMapper){
                if(videoMapper){
                    adminStudyMapper.setArticleImgVideo(article);
                }else {
                    adminStudyMapper.setArticleImgAudio(article);
                }
            } else {
                adminStudyMapper.setArticleImg(article);
            }
        }else {
            if(mediaMapper){
                if(videoMapper){
                    adminStudyMapper.setArticleVideo(article);
                }else {
                    adminStudyMapper.setArticleAudio(article);
                }
            } else {
                adminStudyMapper.setArticleNoAll(article);
            }
        }
        return true;
    }

    public boolean removeArticle(int articleId) {
        String oldImg = adminStudyMapper.getArticleImg(articleId);
        if (oldImg!=null){
            removeImg(oldImg);
        }
        String oldAudio = adminStudyMapper.getArticleAudio(articleId);
        if (oldAudio!=null){
            removeImg(oldAudio);
        }
        String oldVideo = adminStudyMapper.getArticleVideo(articleId);
        if (oldVideo!=null){
            removeImg(oldVideo);
        }

        adminStudyMapper.removeArticle(articleId);
        return true;
    }

    public boolean addSentence(Sentence sentence) {
        if (sentence.getOrder()==null||sentence.getOrder()==0){
            Integer maxOrder = adminStudyMapper.getMaxOrder(sentence.getArticleId());
            if (maxOrder==null){
                sentence.setOrder(1);
            } else {
                sentence.setOrder(maxOrder+1);
            }
        }
        return adminStudyMapper.addSentence(sentence);
    }

    public boolean setSentence(Sentence sentence) {
        return adminStudyMapper.setSentence(sentence);
    }

    public boolean removeSentence(int sentenceId) {
        return adminStudyMapper.removeSentence(sentenceId);
    }
}
