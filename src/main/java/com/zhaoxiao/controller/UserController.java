package com.zhaoxiao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhaoxiao.entity.mine.Message;
import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.model.mine.Login;
import com.zhaoxiao.model.mine.CalendarInfo;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@BaseResponse
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getList")
    public List<User> getList(){
        return userService.getList();
    }

    @GetMapping("/getByAccount")
    public User getByAccount(String account){
        return userService.getByAccount(account);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user){
        userService.addUser(user);
        return true;
    }

    @PostMapping("/loginByPassword")
    public Login loginByPassword(String account, String password){
        return userService.loginByPassword(account,password);
    }

    @PostMapping("/loginByCode")
    public Login loginByCode(String phone){
        return userService.loginByCode(phone);
    }

    @PostMapping("/register")
    public boolean register(String phone, String password, String name){
        return userService.register(phone,password,name);
    }

    @PostMapping("/setPassword")
    public boolean setPassword(String account, String oldPassword, String newPassword){
        return userService.setPassword(account,oldPassword,newPassword);
    }

    @GetMapping("/getPlan")
    public Plan getPlan(String account){
        return userService.getPlan(account);
    }

    @GetMapping("/getCurrentPlan")
    public Long getCurrentPlan(String account){
//        String currentPlan = userService.getCurrentPlan(account);
//        Map<String,String> map = new HashMap<>();
//        map.put("currentPlan",currentPlan);
//        return map;
        return userService.getCurrentPlan(account);
    }

    @GetMapping("/setPlan")
    public boolean setPlan(String account, long plan){
        return userService.setPlan(account,plan);
    }

    @GetMapping("/addPlanDo")
    public boolean addPlanDo(String account, long planDo){
        return userService.addPlanDo(account,planDo);
    }

    @GetMapping("/getPlanList")
    public List<Plan> getPlanList(String account){
        Date testDate = new Date();
        return userService.getPlanList(account);
    }

    @GetMapping("/getCalendarInfo")
    public CalendarInfo getCalendarInfo(String account){
        return userService.getCalendarInfo(account);
    }

    @GetMapping("/getOfficialMessage")
    public PageInfo<Message> getOfficialMessage(@RequestParam(defaultValue = "1") int pageNo,
                                                @RequestParam(defaultValue = "8") int pageSize,
                                                String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(userService.getOfficialMessage(account));
    }

    @PostMapping("/setUser")
    public boolean setUser(String account, String name, String sign, String sex, int age, String mail, MultipartFile imgFile){
        if (imgFile==null||imgFile.isEmpty()) {
            return userService.setUserNoImg(account, name, sign, sex, age, mail);
        }
        boolean isImage = false;
        try {
            // 尝试读取上传的文件为图片
            BufferedImage image = ImageIO.read(imgFile.getInputStream());
            if (image != null) {
                // 如果能够成功读取到图片，则说明上传的文件为图片
                return userService.setUser(account, name, sign, sex, age, mail, imgFile);
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/getPassword")
    public boolean getPassword(String account){
        return userService.getPassword(account);
    }

    @GetMapping("/addFeedback")
    public boolean addFeedback(String account,String info){
        return userService.addFeedback(account,info);
    }

    @GetMapping("/getMyTrendList")
    public PageInfo<TrendM> getMyTrendList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         @RequestParam(defaultValue = "") String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(userService.getMyTrendList(account));
    }

    @GetMapping("/getMyAttentionList")
    public PageInfo<User> getMyAttentionList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         @RequestParam(defaultValue = "") String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(userService.getMyAttentionList(account));
    }

    @GetMapping("/getMyFanList")
    public PageInfo<User> getMyFanList(@RequestParam(defaultValue = "1") int pageNo,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         @RequestParam(defaultValue = "") String account){
        PageHelper.startPage(pageNo,pageSize);
        return new PageInfo<>(userService.getMyFanList(account));
    }
}
