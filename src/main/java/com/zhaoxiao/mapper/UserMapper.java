package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getList();

    @Select("select * from user where account=#{account}")
    List<User> getByAccount(int account);

    @Insert("insert into user(account,password,name,age,sex) values(#{account},#{password},#{name},#{age},#{sex})")
    void addUser(User user);

    void loginByPassword(String account, String password);

    @Select("select account from user where account=#{account} and password=#{password}")
    String selectAccountPassword(String account, String password);

    @Select("select account from user where phone=#{phone} and password=#{password}")
    String selectPhonePassword(String phone, String password);
}
