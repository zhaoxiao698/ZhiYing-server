package com.zhaoxiao.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.mine.AddAdmin;
import com.zhaoxiao.model.mine.AdminAccount;
import com.zhaoxiao.model.mine.AdminLogin;
import com.zhaoxiao.model.mine.Feedback;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

@Api(tags = "4-用户管理")
@RestController
@BaseResponse
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public AdminLogin loginByCode(@RequestBody AdminAccount adminAccount){
        return adminUserService.login(adminAccount);
    }

    @ApiOperation("获取用户列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
    })
    @GetMapping("/getUserList")
    public PageInfo<User> getUserList(/*String account*/@RequestParam(defaultValue = "1") int pageNo,
                                                        @RequestParam(defaultValue = "8") int pageSize){
//        return adminUserService.getUserList(account);
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminUserService.getUserList(0));
    }

    @ApiOperation("重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true),
    })
    @GetMapping("/resetPassword")
    public boolean resetPassword(String account){
        return adminUserService.resetPassword(account);
    }

    @ApiOperation("冻结用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true),
            @ApiImplicitParam(name = "isFreeze",value = "true为冻结，false为解冻（根据用户的状态决定操作）",required = true),
    })
    @GetMapping("/freezeUser")
    public boolean freezeUser(String account, boolean isFreeze){
        return adminUserService.freezeUser(account,isFreeze);
    }

    @ApiOperation(value = "获取管理员列表（分页）（超级管理员才能操作）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
    })
    @GetMapping("/getAdminList")
    public PageInfo<User> getAdminList(@RequestParam(defaultValue = "1") int pageNo,
                                   @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminUserService.getUserList(1));
    }

    @ApiOperation(value = "添加管理员（超级管理员才能操作）",notes = "后端自动生成账号密码返回，不用传参")
    @GetMapping("/addAdmin")
    public AddAdmin addAdmin(){
        return adminUserService.addAdmin();
    }

    @ApiOperation("删除管理员（超级管理员才能操作）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "要删除的账号",required = true),
    })
    @GetMapping("/removeAdmin")
    public boolean removeAdmin(String account){
        return adminUserService.deleteAdmin(account);
    }

    @ApiOperation("获取反馈列表（分页）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo",value = "第几页",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize",value = "每页数量",defaultValue = "8"),
    })
    @GetMapping("/getFeedbackList")
    public PageInfo<Feedback> getFeedbackList(@RequestParam(defaultValue = "1") int pageNo,
                                          @RequestParam(defaultValue = "8") int pageSize){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(adminUserService.getFeedbackList());
    }

    @ApiOperation(value = "处理反馈",notes = "返回反馈列表返回的状态如是未处理，才能处理，处理后变为已处理状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "反馈id",required = true),
    })
    @GetMapping("/handleFeedback")
    public boolean handleFeedback(int id){
        return adminUserService.handleFeedback(id);
    }

    @ApiOperation(value = "回复反馈消息", notes = "向反馈的用户回复一条消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "receiveAccount",value = "反馈的用户",required = true),
            @ApiImplicitParam(name = "info",value = "回复的消息",required = true),
    })
    @GetMapping("/sendMessage")
    public boolean sendMessage(String receiveAccount, String info){
        return adminUserService.sendMessage(receiveAccount,info);
    }

    @ApiOperation(value = "查询用户信息", notes = "用于查看修改登录的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true),
    })
    @GetMapping("/getUser")
    public User getUser(String account){
        return adminUserService.getUser(account);
    }

    @ApiOperation(value = "修改用户信息", notes = "只用于修改登陆的用户，不可修改其他用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "account",value = "账号",required = true),
            @ApiImplicitParam(name = "phone",value = "手机号",required = true),
            @ApiImplicitParam(name = "name",value = "名字",required = true),
            @ApiImplicitParam(name = "(avatarFile)",value = "头像文件--只能上传图片文件",required = true),
            @ApiImplicitParam(name = "age",value = "年龄",required = true),
            @ApiImplicitParam(name = "sex",value = "性别",required = true),
    })
    @PostMapping("/setUser")
    public boolean setUser(String account,
                           String phone,
                           String name,
                           MultipartFile avatarFile,
                           int age,
                           String sex){
        if (avatarFile==null||avatarFile.isEmpty()) {
            // 如果上传的文件为空，则返回错误信息或者抛出异常
            // ...
            return adminUserService.setUserWithNoImg(account, phone, name, age, sex);
        }
        boolean isImage = false;
        try {
            // 尝试读取上传的文件为图片
            BufferedImage image = ImageIO.read(avatarFile.getInputStream());
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
            return adminUserService.setUser(account, phone, name, avatarFile, age, sex);
        } else {
            // 如果上传的不是图片，则返回错误信息或者抛出异常
            // ...
            return false;
        }
    }

    @ApiOperation(value = "修改密码", notes = "只用于修改登录的用户的密码")
    @PostMapping("/setPassword")
    public boolean setPassword(@RequestBody AdminAccount adminAccount){
        return adminUserService.setPassword(adminAccount);
    }
}
