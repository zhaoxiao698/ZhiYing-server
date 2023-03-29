package com.zhaoxiao.controller;

import com.zhaoxiao.entity.User;
import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public boolean loginByPassword(String account, String password){
        return userService.loginByPassword(account,password);
    }
}
