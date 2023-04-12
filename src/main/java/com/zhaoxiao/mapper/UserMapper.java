package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.mine.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getList();

    @Select("select * from user where account=#{account}")
    List<User> getByAccount(int account);

    @Insert("insert into user(account,password,phone,name,age,sex) values(#{account},#{password},#{phone},#{name},#{age},#{sex})")
    boolean addUser(User user);

    void loginByPassword(String account, String password);

    @Select("select account from user where account=#{account} and password=#{password}")
    String selectAccountPassword(String account, String password);

    @Select("select account from user where phone=#{phone} and password=#{password}")
    String selectPhonePassword(String phone, String password);

    @Select("select phone from user where phone=#{phone}")
    String selectPhone(String phone);

    @Select("select account from user")
    List<String> getAccountList();

    @Select("select account from user where phone = #{phone}")
    String getAccountByPhone(String phone);
}
