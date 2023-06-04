package com.zhaoxiao.mapper.admin;

import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.mine.AdminAccount;
import com.zhaoxiao.model.mine.Feedback;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminUserMapper {
    @Select("select account from user where account=#{account} and password=#{password}")
    String selectAccountPassword(String account, String password);

    @Select("select permissions from user where account=#{account}")
    Integer selectPermissions(String account);

    @Select("select account,password,phone,name,avatar,age,sex,permissions,addTime,status,sign,mail from user where permissions=#{permissions}")
    List<User> getUserList(int permissions);

    @Update("update user set password='123456' where account=#{account}")
    boolean resetPassword(String account);

    @Update("update user set status=#{status} where account=#{account}")
    boolean freezeUser(String account, int status);

    @Select("select account from user")
    List<String> getAccountList();

    @Insert("insert into user(account,password,name,permissions) values(#{account},#{password},#{name},#{permissions})")
    void addAdmin(User user);

    @Delete("delete from user where account=#{account} and permissions=1")
    boolean deleteAdmin(String account);

    @Select("select * from feedback")
    List<Feedback> getFeedbackList();

    @Update("update feedback set handle=1 where id=#{id}")
    boolean handleFeedback(int id);

    @Insert("insert into message(info,receiveAccount) values(#{info},#{receiveAccount})")
    boolean sendMessage(String receiveAccount, String info);

    @Select("select account,password,phone,name,avatar,age,sex,permissions,addTime,status,sign,mail from user where account=#{account}")
    User getUser(String account);

    @Update("update user set phone=#{phone},name=#{name},avatar=#{avatar},age=#{age},sex=#{sex},sign=#{sign},mail=#{mail} where account=#{account}")
    boolean setUser(User user);

    @Update("update user set password=#{password} where account=#{account}")
    boolean setPassword(AdminAccount adminAccount);

    @Select("select avatar from user where account=#{account}")
    String getUserAvatar(String account);

    @Update("update user set phone=#{phone},name=#{name},age=#{age},sex=#{sex},sign=#{sign},mail=#{mail} where account=#{account}")
    boolean setUserWithNoImg(User user);

    @Select("select password from user where account=#{account}")
    String getPassword(String account);

//    @Update("update user set password=#{newPassword} where account=#{account}")
//    boolean setPassword(String account, String newPassword);
}
