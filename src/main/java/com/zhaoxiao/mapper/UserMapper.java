package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.mine.Message;
import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.model.community.TrendM;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    List<User> getList();

    @Select("select * from user where account=#{account}")
    User getByAccount(String account);

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

    @Update("update user set password=#{newPassword} where account=#{account}")
    boolean addPassword(String account, String newPassword);

    @Select("select * from plan where userAccount = #{account} and DATE(addTime) = DATE(NOW())")
    Plan getPlan(String account);

    @Select("select plan from currentPlan where userAccount = #{account}")
    Long getCurrentPlan(String account);

    @Insert("insert into currentPlan(userAccount,plan) values(#{account},#{plan})")
    void addCurrentPlan(String account, long plan);

    @Update("update currentPlan set plan=#{plan} where userAccount=#{account}")
    void setCurrentPlan(String account, long plan);

    @Insert("insert into plan(userAccount,plan) values(#{account},#{plan})")
    void addPlan(String account, long plan);

    @Update("update plan set plan=#{plan} where userAccount=#{account} and DATE(addTime) = DATE(NOW())")
    void setPlan(String account, long plan);

    @Insert("insert into plan(userAccount,plan,planDo) values(#{account},#{currentPlan},#{planDo})")
    void addPlanDo(String account, Long currentPlan, long planDo);

    @Update("update plan set planDo=planDo+#{planDo} where userAccount=#{account} and DATE(addTime) = DATE(NOW())")
    void setPlanDo(String account, long planDo);

    @Select("select * from plan where userAccount = #{account}")
    List<Plan> getPlanList(String account);

    @Select("select count(*) from plan where userAccount = #{account} and planDo>=plan")
    int getTotalDays(String account);

    @Select("select * from message where receiveAccount = #{account} and sendAccount=''")
    List<Message> getOfficialMessage(String account);

    @Update("update user set name=#{name},sign=#{sign},sex=#{sex},age=#{age},mail=#{mail},avatar=#{avatar} where account=#{account}")
    void setUser(User user);

    @Update("update user set name=#{name},sign=#{sign},sex=#{sex},age=#{age},mail=#{mail} where account=#{account}")
    void setUserNoImg(User user);

    @Select("select avatar from user where account=#{account}")
    String getUserAvatar(String account);

    @Select("select password from user where account=#{account}")
    String getPassword(String account);

    @Update("update user set password=#{newPassword} where account=#{account}")
    boolean setPassword(String account, String newPassword);

    @Select("select password from user where account=#{account} and password=#{oldPassword}")
    String getOldPassword(String account, String oldPassword);

    @Insert("insert into feedback(userAccount,info) values(#{account},#{info})")
    boolean addFeedback(String account, String info);

    @Select("select count(1) from trend where userAccount=#{account}")
    int getTrendNum(String account);

    @Select("select count(1) from attention where fanAccount=#{account}")
    int getAttentionNum(String account);

    @Select("select count(1) from attention where userAccount=#{account}")
    int getFanNum(String account);

    @Select("select id,userAccount,name userName,avatar userAvatar,title,info,trend.addTime,`like`,collection,comment,share,linkId,linkType,linkTable from trend join user on user.account=trend.userAccount where user.account=#{account}")
    List<TrendM> getMyTrendList(String account);

    @Select("select * from user join attention on attention.userAccount=user.account where fanAccount=#{account}")
    List<User> getMyAttentionList(String account);

    @Select("select * from user join attention on attention.fanAccount=user.account where userAccount=#{account}")
    List<User> getMyFanList(String account);

    @Select("select password from user where account=#{account}")
    String getPasswordTest(String account);

    @Select("select password from user where phone=#{phone} limit 1")
    String getPasswordByPhone(String phone);
}
