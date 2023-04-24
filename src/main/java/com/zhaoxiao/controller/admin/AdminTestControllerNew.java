package com.zhaoxiao.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.test.BankedQuestion;
import com.zhaoxiao.entity.test.ListeningQuestion;
import com.zhaoxiao.model.test.*;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminTestService;
import com.zhaoxiao.service.admin.AdminTestServiceNew;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(tags = "2-测试管理")
@RestController
@BaseResponse
@RequestMapping("/admin/test")
public class AdminTestControllerNew {
    @Autowired
    private AdminTestServiceNew adminTestServiceNew;

    @ApiOperation("获取题目列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
            @ApiImplicitParam(name = "questionBankId",value = "题库id",required = true),
            @ApiImplicitParam(name = "type",value = "题目类型id（二级分类）",required = true),
    })
    @GetMapping("/getQuestionList")
    public PageInfo<QuestionNew> getQuestionList(@RequestParam(defaultValue = "1") int pageNo,
                                                 @RequestParam(defaultValue = "8") int pageSize,
                                                 int questionBankId, int type){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminTestServiceNew.getQuestionList(questionBankId,type));
    }

    @ApiOperation(value = "获取类型列表",notes = "这是一级分类列表，内部包含二级分类列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionBankId",value = "题库id",required = true),
    })
    @GetMapping("/getTestFtypeList")
    public List<TestFtype> getTestFtypeList(int questionBankId){
        return adminTestServiceNew.getTestFtypeList(questionBankId);
    }

    @ApiIgnore
    @PostMapping("/addQuestion1")
    public boolean addQuestion1(@RequestBody QuestionNew questionNew){
        return adminTestServiceNew.addQuestion1(questionNew);
    }

    @ApiOperation(value = "添加父题目",notes = "不同类型题目需要添加的不同，但是后端会判断，你只要所有类型都弄一套输入框或者文件选择框把下面的参数传过来就行")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info",value = "题目内容--注意：这里是要用富文本编辑器然后传入富文本编辑器生成的html",required = true),
            @ApiImplicitParam(name = "questionBankId",value = "题库id",required = true),
            @ApiImplicitParam(name = "word",value = "单词列表"),
            @ApiImplicitParam(name = "audioFile",value = "音频文件--只能传入音频文件"),
            @ApiImplicitParam(name = "type",value = "类型id（二级分类）",required = true),
            @ApiImplicitParam(name = "num",value = "段落数量"),
            @ApiImplicitParam(name = "A",value = "A选项"),
            @ApiImplicitParam(name = "B",value = "B选项"),
            @ApiImplicitParam(name = "C",value = "C选项"),
            @ApiImplicitParam(name = "D",value = "D选项"),
            @ApiImplicitParam(name = "E",value = "E选项"),
            @ApiImplicitParam(name = "F",value = "F选项"),
            @ApiImplicitParam(name = "G",value = "G选项"),
            @ApiImplicitParam(name = "answer",value = "答案"),
            @ApiImplicitParam(name = "imgFile",value = "图片文件--只能传入图片文件"),
    })
    @PostMapping("/addQuestion")
    public boolean addQuestion(String info,
                               int questionBankId,
                               String word,
                               MultipartFile audioFile,
                               int type,
                               int num,
                               String A,
                               String B,
                               String C,
                               String D,
                               String E,
                               String F,
                               String G,
                               String answer,
                               MultipartFile imgFile){
        return adminTestServiceNew.addQuestion(info, questionBankId, word, audioFile, type, num, A, B, C, D, E, F, G, answer, imgFile);
    }

    @ApiOperation(value = "添加子题目",notes = "和添加父题目一样只要弄输入框把需要的参数传进来就行，只是这个没有文件和富文本的需求")
    @PostMapping("/addSubQuestion")
    public boolean addSubQuestion(@RequestBody SubQuestionNew subQuestionNew){
        return adminTestServiceNew.addSubQuestion(subQuestionNew);
    }

    @ApiOperation(value = "修改父题目",notes = "除了需要传入需要修改的父题目的id，其他参数和添加父题目一样")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "需要修改的父题目的id"),
            @ApiImplicitParam(name = "info",value = "题目内容--注意：这里是要用富文本编辑器然后传入富文本编辑器生成的html",required = true),
            @ApiImplicitParam(name = "questionBankId",value = "题库id",required = true),
            @ApiImplicitParam(name = "word",value = "单词列表"),
            @ApiImplicitParam(name = "audioFile",value = "音频文件--只能传入音频文件"),
            @ApiImplicitParam(name = "type",value = "类型id（二级分类）",required = true),
            @ApiImplicitParam(name = "num",value = "段落数量"),
            @ApiImplicitParam(name = "A",value = "A选项"),
            @ApiImplicitParam(name = "B",value = "B选项"),
            @ApiImplicitParam(name = "C",value = "C选项"),
            @ApiImplicitParam(name = "D",value = "D选项"),
            @ApiImplicitParam(name = "E",value = "E选项"),
            @ApiImplicitParam(name = "F",value = "F选项"),
            @ApiImplicitParam(name = "G",value = "G选项"),
            @ApiImplicitParam(name = "answer",value = "答案"),
            @ApiImplicitParam(name = "imgFile",value = "图片文件--只能传入图片文件"),
    })
    @PostMapping("/setQuestion")
    public boolean setQuestion(int id,
                               String info,
                               int questionBankId,
                               String word,
                               MultipartFile audioFile,
                               int type,
                               int num,
                               String A,
                               String B,
                               String C,
                               String D,
                               String E,
                               String F,
                               String G,
                               String answer,
                               MultipartFile imgFile){
        return adminTestServiceNew.setQuestion(id, info, questionBankId, word, audioFile, type, num, A, B, C, D, E, F, G, answer, imgFile);
    }

    @ApiOperation(value = "修改子题目",notes = "除了需要传入需要修改的子题目的id，其他参数和添加子题目一样")
    @PostMapping("/setSubQuestion")
    public boolean setSubQuestion(@RequestBody SubQuestionNew subQuestionNew){
        return adminTestServiceNew.setSubQuestion(subQuestionNew);
    }

    @ApiOperation("删除父题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "父题目id",required = true),
            @ApiImplicitParam(name = "type",value = "类型id（二级分类）",required = true),
    })
    @GetMapping("/removeQuestion")
    public boolean removeQuestion(int id,int type){
        return adminTestServiceNew.removeQuestion(id,type);
    }

    @ApiOperation("删除子题目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "子题目id",required = true),
            @ApiImplicitParam(name = "type",value = "类型id（二级分类）",required = true),
    })
    @GetMapping("/removeSubQuestion")
    public boolean removeSubQuestion(int id,int type){
        return adminTestServiceNew.removeSubQuestion(id,type);
    }
}
