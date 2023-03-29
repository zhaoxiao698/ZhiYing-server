package com.zhaoxiao.service;

import com.zhaoxiao.entity.User;
import com.zhaoxiao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getList() {
        return userMapper.getList();
    }

    public List<User> getByAccount(int account) {
        return userMapper.getByAccount(account);
    }

    public void addUser(User user) {
        userMapper.addUser(user);
    }

    public boolean loginByPassword(String account, String password) {
        if(userMapper.selectAccountPassword(account,password)==null){
            return userMapper.selectPhonePassword(account, password) != null;
        }else {
            return true;
        }
    }
}
