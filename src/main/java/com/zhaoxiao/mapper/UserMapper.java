package com.zhaoxiao.mapper;

import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
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

    @Update("update user set password=#{password} where account=#{account}")
    boolean setPassword(String account, String password);

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
}
