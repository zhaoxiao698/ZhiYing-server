package com.zhaoxiao.service;

import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.mapper.UserMapper;
import com.zhaoxiao.model.mine.Login;
import com.zhaoxiao.model.mine.CalendarInfo;
import com.zhaoxiao.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        List<String> accountNewList = AccountUtil.getUserIds(accountOldList,1,"zhiying");
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
            List<String> accountNewList = AccountUtil.getUserIds(accountOldList,1,"zhiying");
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

    public boolean setPassword(String account, String password) {
        return userMapper.setPassword(account,password);
    }

    public Plan getPlan(String account) {
        Plan plan = userMapper.getPlan(account);
        if (plan==null){
            plan = new Plan();
            plan.setUserAccount(account);
            Long planI = getCurrentPlan(account);
            if (planI==null){
                planI = 0L;
            }
            plan.setPlan(planI);
            plan.setPlanDo(0);
        }
        return plan;
    }

    public Long getCurrentPlan(String account) {
        return userMapper.getCurrentPlan(account);
    }

    public boolean setPlan(String account, long plan) {
        if (userMapper.getCurrentPlan(account)==null){
            userMapper.addCurrentPlan(account,plan);
        } else {
            userMapper.setCurrentPlan(account,plan);
        }
        if (userMapper.getPlan(account)==null){
            userMapper.addPlan(account,plan);
        } else {
            userMapper.setPlan(account,plan);
        }
        return true;
    }

    public boolean addPlanDo(String account, long planDo) {
        if (userMapper.getPlan(account)==null){
            //查当前计划
            Long currentPlan = userMapper.getCurrentPlan(account);
            if (currentPlan == null){
                currentPlan = 0L;
                userMapper.addCurrentPlan(account,currentPlan);
            }
            userMapper.addPlanDo(account,currentPlan,planDo);
        } else {
            userMapper.setPlanDo(account,planDo);
        }
        return true;
    }

    public List<Plan> getPlanList(String account) {
        return userMapper.getPlanList(account);
    }

    public CalendarInfo getCalendarInfo(String account) {
        CalendarInfo calendarInfo = new CalendarInfo();
        List<Plan> planList = getPlanList(account);
        calendarInfo.setPlanList(planList);
        calendarInfo.setContinuous(getContinuousDays(planList));
        calendarInfo.setTotal(userMapper.getTotalDays(account));
        return calendarInfo;
    }

    private int getContinuousDays1(List<Plan> planList){
        // 获取计划完成日期的列表
        List<Date> completedDates = new ArrayList<>();
        for (Plan plan : planList) {
            if (plan.getPlanDo() >= plan.getPlan()) {
                completedDates.add(plan.getAddTime());
            }
        }

        // 根据完成日期列表计算连续天数
        int continuousDays = 0;
        if (!completedDates.isEmpty()) {
            // 将完成日期列表按照时间戳从小到大排序
            Collections.sort(completedDates);
            // 判断最后一次完成的日期是否是今天或昨天
            Date lastCompletedDate = completedDates.get(completedDates.size() - 1);
            Date today = new Date();
            Date yesterday = new Date(today.getTime() - 24 * 60 * 60 * 1000);
            if (lastCompletedDate.compareTo(today) >= 0) {
                // 最后一次完成的日期是今天或之后，从今天开始往前数连续天数
                continuousDays = 1;
                for (int i = completedDates.size() - 2; i >= 0; i--) {
                    System.out.println("1:"+completedDates.get(i));
                    System.out.println("2:"+new Date(today.getTime() - continuousDays * 24 * 60 * 60 * 1000));
                    if (completedDates.get(i).compareTo(new Date(today.getTime() - continuousDays * 24 * 60 * 60 * 1000)) == 0) {
                        continuousDays++;
                    } else {
                        break;
                    }
                }
            } else if (lastCompletedDate.compareTo(yesterday) >= 0) {
                // 最后一次完成的日期是昨天，从昨天开始往前数连续天数
                continuousDays = 1;
                for (int i = completedDates.size() - 2; i >= 0; i--) {
                    System.out.println("1:"+completedDates.get(i));
                    System.out.println("2:"+new Date(yesterday.getTime() - continuousDays * 24 * 60 * 60 * 1000));
                    if (completedDates.get(i).compareTo(new Date(yesterday.getTime() - continuousDays * 24 * 60 * 60 * 1000)) == 0) {
                        continuousDays++;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println("连续完成计划的天数为：" + continuousDays);
        return continuousDays;
    }

    private int getContinuousDays(List<Plan> planList){
        // 获取计划完成日期的列表
        List<LocalDate> completedDates = new ArrayList<>();
        for (Plan plan : planList) {
            if (plan.getPlanDo() >= plan.getPlan()) {
                LocalDate completedDate = plan.getAddTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                completedDates.add(completedDate);
            }
        }

        // 根据完成日期列表计算连续天数
        int continuousDays = 0;
        if (!completedDates.isEmpty()) {
            // 将完成日期列表按照日期从小到大排序
            Collections.sort(completedDates);
            // 判断最后一次完成的日期是否是今天或昨天
            LocalDate lastCompletedDate = completedDates.get(completedDates.size() - 1);
            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);
            if (lastCompletedDate.compareTo(today) >= 0) {
                // 最后一次完成的日期是今天或之后，从今天开始往前数连续天数
                continuousDays = 1;
                for (int i = completedDates.size() - 2; i >= 0; i--) {
                    if (completedDates.get(i).equals(today.minusDays(continuousDays))) {
                        continuousDays++;
                    } else {
                        break;
                    }
                }
            } else if (lastCompletedDate.compareTo(yesterday) >= 0) {
                // 最后一次完成的日期是昨天，从昨天开始往前数连续天数
                continuousDays = 1;
                for (int i = completedDates.size() - 2; i >= 0; i--) {
                    if (completedDates.get(i).equals(yesterday.minusDays(continuousDays))) {
                        continuousDays++;
                    } else {
                        break;
                    }
                }
            }
        }

        System.out.println("连续完成计划的天数为：" + continuousDays);
        return continuousDays;
    }
}
