package com.zhaoxiao.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.study.Sentence;
import com.zhaoxiao.entity.study.Stype;
import com.zhaoxiao.model.mine.AdminLogin;
import com.zhaoxiao.model.study.ArticleDetailM;
import com.zhaoxiao.model.study.ArticleM;
import com.zhaoxiao.model.study.ChannelM;
import com.zhaoxiao.model.study.Ftype;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminStudyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(tags = "1-学习管理")
@RestController
@BaseResponse
@RequestMapping("/admin/study")
public class AdminStudyController {
    @Autowired
    private AdminStudyService adminStudyService;

    @ApiOperation(value = "获取类型列表",notes = "这是一级分类列表，内部包含二级分类列表",position = 1)
    @GetMapping("/getFtypeList")
    public List<Ftype> getFtypeList(){
        return adminStudyService.getTypeList();
    }

    @ApiIgnore
    @ApiOperation(value = "通过id获取一级分类",notes = "一级分类，内部包含二级分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "一级分类id",required = true),
    })
    @GetMapping("/getFtypeById")
    public Ftype getFtypeById(int id){
        return adminStudyService.getFtypeById(id);
    }

//    @ApiIgnore
    @ApiOperation(value = "获取频道列表（分页）",position = 8)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
            @ApiImplicitParam(name = "stypeId",value = "所属二级分类id",required = true),
    })
    @GetMapping("/getChannelList")
    public PageInfo<ChannelM> getChannelList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             /*int ftypeId, */int stypeId){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminStudyService.getChannelList(/*ftypeId,*/stypeId));
    }

    @ApiOperation(value = "通过id获取频道",position = 9)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "频道id",required = true),
    })
    @GetMapping("/getChannelById")
    public ChannelM getChannelById(int id){
        return adminStudyService.getChannelById(id);
    }

    @ApiOperation(value = "获取文章列表（分页）",position = 13)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
            @ApiImplicitParam(name = "channelId",value = "频道id",required = true),
    })
    @GetMapping("/getArticleList")
    public PageInfo<ArticleM> getArticleList(@RequestParam(defaultValue = "1") int pageNo,
                                             @RequestParam(defaultValue = "8") int pageSize,
                                             int channelId/*,
                                             @RequestParam(name = "title",defaultValue = "false") boolean sort,
                                             @RequestParam(name = "asc",defaultValue = "false") boolean order*/){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminStudyService.getArticleList(channelId/*,sort,order*/));
    }

    @ApiOperation(value = "获取文章详情（包含句子等信息）",position = 14)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id",required = true),
    })
    @GetMapping("/getArticleDetail")
    public ArticleDetailM getArticleDetail(int id){
        return adminStudyService.getArticleDetail(id);
    }

    @ApiOperation(value = "添加一级分类",position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "名称",required = true),
    })
    @GetMapping("/addFtype")
    public boolean addFtype(String name){
        return adminStudyService.addFtype(name);
    }

    @ApiOperation(value = "添加二级分类",position = 3)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ftypeId",value = "所属一级分类id",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true),
    })
    @GetMapping("/addStype")
    public boolean addStype(int ftypeId, String name){
        return adminStudyService.addStype(ftypeId,name);
    }

    @ApiOperation(value = "修改一级分类",position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "一级分类id",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true),
    })
    @GetMapping("/setFtype")
    public boolean setFtype(int id, String name){
        return adminStudyService.setFtype(id,name);
    }

    @ApiOperation(value = "修改二级分类",position = 5)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "二级分类id",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true),
    })
    @GetMapping("/setStype")
    public boolean setStype(int id, String name){
        return adminStudyService.setStype(id,name);
    }

    @ApiOperation(value = "删除一级分类",position = 6)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "一级分类id",required = true),
    })
    @GetMapping("/removeFtype")
    public Map<String,Object> removeFtype(int id){
        return adminStudyService.removeFtype(id);
    }

    @ApiOperation(value = "删除二级分类",position = 7)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "二级分类id",required = true),
    })
    @GetMapping("/removeStype")
    public Map<String,Object> removeStype(int id){
        return adminStudyService.removeStype(id);
    }

//    @PostMapping("/addChannel")
//    public boolean addChannel(@RequestBody ChannelM channelM){
//        return adminStudyService.addChannel(channelM);
//    }

    @ApiIgnore
    @PostMapping("/testPost")
    public String addChannel(@RequestBody String name){
        return name;
    }

    @ApiOperation(value = "添加频道",position = 10)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stypeId",value = "所属二级分类id",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true),
            @ApiImplicitParam(name = "info",value = "描述",required = true),
            @ApiImplicitParam(name = "(imgFile)",value = "图片文件--只能传入图片文件",required = true),
    })
    @PostMapping("/addChannel")
    public boolean addChannel(int stypeId, String name, String info, MultipartFile imgFile){
        boolean isImage = false;
        try {
            // 尝试读取上传的文件为图片
            BufferedImage image = ImageIO.read(imgFile.getInputStream());
            if (image != null) {
                // 如果能够成功读取到图片，则说明上传的文件为图片
                isImage = true;
                // 进行相应的处理，例如将图片保存到磁盘上
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 根据isImage的值返回相应的结果
        if (isImage) {
            return adminStudyService.addChannel(stypeId,name,info,imgFile);
        } else {
            // 如果上传的不是图片，则返回错误信息或者抛出异常
            // ...
            return false;
        }
    }

    @ApiOperation(value = "修改频道",notes = "除了要传入需要修改的频道的id，其他和添加频道一样",position = 11)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "频道id",required = true),
            @ApiImplicitParam(name = "stypeId",value = "所属二级分类id",required = true),
            @ApiImplicitParam(name = "name",value = "名称",required = true),
            @ApiImplicitParam(name = "info",value = "描述",required = true),
            @ApiImplicitParam(name = "(imgFile)",value = "图片文件--只能传入图片文件",required = true),
    })
    @PostMapping("/setChannel")
    public boolean setChannel(int id, String name, String info, MultipartFile imgFile){
        if (imgFile==null||imgFile.isEmpty()) {
            // 如果上传的文件为空，则返回错误信息或者抛出异常
            // ...
            return adminStudyService.setChannelWithNoImg(id,name,info);
        }
        boolean isImage = false;
        try {
            // 尝试读取上传的文件为图片
            BufferedImage image = ImageIO.read(imgFile.getInputStream());
            if (image != null) {
                // 如果能够成功读取到图片，则说明上传的文件为图片
                isImage = true;
                // 进行相应的处理，例如将图片保存到磁盘上
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 根据isImage的值返回相应的结果
        if (isImage) {
            return adminStudyService.setChannel(id,name,info,imgFile);
        } else {
            // 如果上传的不是图片，则返回错误信息或者抛出异常
            // ...
            return false;
        }
    }

    @ApiOperation(value = "删除频道",position = 12)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "频道id",required = true),
    })
    @GetMapping("/removeChannel")
    public boolean removeChannel(int id){
        return adminStudyService.removeChannel(id);
    }

    @ApiOperation(value = "添加文章",position = 15)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "channelId",value = "所属频道id",required = true),
            @ApiImplicitParam(name = "title",value = "标题",required = true),
            @ApiImplicitParam(name = "(imgFile)",value = "封面图片文件--只能传入图片文件",required = true),
            @ApiImplicitParam(name = "(mediaFile)",value = "音频或者视频文件--只能传入音频或者视频文件",required = true),
    })
    @PostMapping("/addArticle")
    public boolean addArticle(int channelId, String title, MultipartFile imgFile, MultipartFile mediaFile){
        if (imgFile == null || imgFile.isEmpty()) {
            return false;
        }
        if (mediaFile == null || mediaFile.isEmpty()) {
            return false;
        }

        try {
            // 尝试读取上传的文件为图片
            BufferedImage image = ImageIO.read(imgFile.getInputStream());
            if (image == null) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String fileName = mediaFile.getOriginalFilename();
        String extension = "";

        if (fileName != null) {
            int lastDotIndex = fileName.lastIndexOf(".");
            if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
                extension = fileName.substring(lastDotIndex + 1);
            }
        }

        if ("mp4".equalsIgnoreCase(extension) || "avi".equalsIgnoreCase(extension) || "mov".equalsIgnoreCase(extension)) {
            return adminStudyService.addArticleVideo(channelId,title,imgFile,mediaFile);
        } else if ("mp3".equalsIgnoreCase(extension) || "wav".equalsIgnoreCase(extension)) {
            return adminStudyService.addArticleAudio(channelId,title,imgFile,mediaFile);
        } else {
            return false;
        }
    }

//    @PostMapping("/setArticle")
//    public boolean setArticle(int channelId, String title, MultipartFile imgFile, MultipartFile mediaFile){
//        if (imgFile==null||imgFile.isEmpty()) {
//            return false;
//        }
//        if (mediaFile==null|| mediaFile.isEmpty()){
//            return false;
//        }
//
//        String fileName = mediaFile.getOriginalFilename();
//        String extension = "";
//
//        if (fileName != null) {
//            int lastDotIndex = fileName.lastIndexOf(".");
//            if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
//                extension = fileName.substring(lastDotIndex + 1);
//            }
//        }
//
//        if ("mp4".equalsIgnoreCase(extension) || "avi".equalsIgnoreCase(extension) || "mov".equalsIgnoreCase(extension)) {
//            return adminStudyService.addArticleVideo(channelId,title,imgFile,mediaFile);
//        } else if ("mp3".equalsIgnoreCase(extension) || "wav".equalsIgnoreCase(extension)) {
//            return adminStudyService.addArticleAudio(channelId,title,imgFile,mediaFile);
//        } else {
//            return false;
//        }
//    }

    @ApiOperation(value = "修改文章",notes = "除了要传入需要修改的文章id，其他和添加文章一样",position = 16)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id",required = true),
            @ApiImplicitParam(name = "channelId",value = "所属频道id",required = true),
            @ApiImplicitParam(name = "title",value = "标题",required = true),
            @ApiImplicitParam(name = "(imgFile)",value = "封面图片文件--只能传入图片文件",type = "file",required = true),
            @ApiImplicitParam(name = "(mediaFile)",value = "音频或者视频文件--只能传入音频或者视频文件",dataType = "file",required = true),
    })
    @PostMapping("/setArticle")
    public boolean setArticle(int id, String title, MultipartFile imgFile, MultipartFile mediaFile){
        return adminStudyService.setArticle(id,title,imgFile,mediaFile);
    }

    @ApiOperation(value = "删除文章",position = 17)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "文章id",required = true),
    })
    @GetMapping("/removeArticle")
    public boolean removeArticle(int id){
        return adminStudyService.removeArticle(id);
    }

    @ApiOperation(value = "添加句子",position = 18)
    @PostMapping("/addSentence")
    public boolean addSentence(@RequestBody Sentence sentence){
        return adminStudyService.addSentence(sentence);
    }

    @ApiOperation(value = "修改句子",notes = "除了要传入需要修改的文章id，其他和添加文章一样")
    @PostMapping("/setSentence")
    public boolean setSentence(@RequestBody Sentence sentence){
        return adminStudyService.setSentence(sentence);
    }

    @ApiOperation("删除句子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "句子id",required = true),
    })
    @GetMapping("/removeSentence")
    public boolean removeSentence(int id){
        return adminStudyService.removeSentence(id);
    }
}
