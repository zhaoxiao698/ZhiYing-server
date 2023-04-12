package com.zhaoxiao.service;

import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.mapper.UserMapper;
import com.zhaoxiao.model.mine.Login;
import com.zhaoxiao.util.AccountUtil;
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

    public Login loginByPassword(String account, String password) {
        Login login;
        if(userMapper.selectAccountPassword(account,password)==null){
            if (userMapper.selectPhonePassword(account, password) == null){
                login = new Login(null, Login.LoginType.ERROR);
            }else {
                login = new Login(userMapper.getAccountByPhone(account), Login.LoginType.LOGIN_BY_PHONE);
            }
        }else {
            login = new Login(account, Login.LoginType.LOGIN_BY_ACCOUNT);
        }
        return login;
    }

    public Login loginByCode(String phone) {
        Login login;
        if(phone.equals(userMapper.selectPhone(phone))){
            login = new Login(userMapper.getAccountByPhone(phone), Login.LoginType.LOGIN_BY_CODE);
        } else {
            //注册
//            register(phone,"","");
            login = new Login(registerByCode(phone), Login.LoginType.REGISTER);
        }
        return login;
    }

    private String registerByCode(String phone) {
        //生成账号
        List<String> accountOldList = userMapper.getAccountList();
        List<String> accountNewList = AccountUtil.getUserIds(accountOldList,1);
        String accountNew = accountNewList.get(0);

        User user = new User();
        user.setAccount(accountNew);
        user.setPhone(phone);
        user.setName("用户"+accountNew);
        userMapper.addUser(user);

        return accountNew;
    }

    public boolean register(String phone, String password, String name) {
        if (phone.equals(userMapper.selectPhone(phone))){
            return false;
        } else {
            //生成账号
            List<String> accountOldList = userMapper.getAccountList();
            List<String> accountNewList = AccountUtil.getUserIds(accountOldList,1);
            String accountNew = accountNewList.get(0);

            User user = new User();
            user.setAccount(accountNew);
            user.setPassword(password);
            user.setPhone(phone);
            if(name==null || name.equals("")) {
                user.setName("用户"+accountNew);
            } else {
                user.setName(name);
            }

            return userMapper.addUser(user);
        }
    }
}
