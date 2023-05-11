package com.zhaoxiao.controller;

import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.mine.Login;
import com.zhaoxiao.model.mine.CalendarInfo;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<User> getByAccount(int account){
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
    public boolean setPassword(String account, String password){
        return userService.setPassword(account,password);
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
}
