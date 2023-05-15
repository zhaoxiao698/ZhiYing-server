package com.zhaoxiao.service;

import com.zhaoxiao.entity.community.ImageViewInfo;
import com.zhaoxiao.entity.mine.Message;
import com.zhaoxiao.entity.mine.Plan;
import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.entity.study.Channel;
import com.zhaoxiao.mapper.CommunityMapper;
import com.zhaoxiao.mapper.UserMapper;
import com.zhaoxiao.model.community.TrendM;
import com.zhaoxiao.model.mine.Login;
import com.zhaoxiao.model.mine.CalendarInfo;
import com.zhaoxiao.util.AccountUtil;
import com.zhaoxiao.util.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommunityMapper communityMapper;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.accessPath}")
    private String accessPath;

    public List<User> getList() {
        return userMapper.getList();
    }

    public User getByAccount(String account) {
        User user = userMapper.getByAccount(account);
        user.setTrendNum(userMapper.getTrendNum(account));
        user.setAttentionNum(userMapper.getAttentionNum(account));
        user.setFanNum(userMapper.getFanNum(account));
        return user;
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

    public boolean setPassword(String account, String oldPassword, String newPassword) {
        if (getPassword(account)){
            if (userMapper.getOldPassword(account,oldPassword)!=null){
                userMapper.setPassword(account,newPassword);
                return true;
            }
            return false;
        }
        userMapper.addPassword(account,newPassword);
        return true;
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

    public List<Message> getOfficialMessage(String account) {
        return userMapper.getOfficialMessage(account);
    }

    public boolean setUser(String account, String name, String sign, String sex, int age, String mail, MultipartFile imgFile) {
        String img = addFile(imgFile,"user/avatar");

        //删除原来的文件
        String oldImg = userMapper.getUserAvatar(account);
        if (oldImg!=null){
            removeImg(oldImg);
        }

        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setSign(sign);
        user.setSex(sex);
        user.setAge(age);
        user.setMail(mail);
        user.setAvatar(img);
        userMapper.setUser(user);
        return true;
    }

    public boolean setUserNoImg(String account, String name, String sign, String sex, int age, String mail) {
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        user.setSign(sign);
        user.setSex(sex);
        user.setAge(age);
        user.setMail(mail);
        userMapper.setUserNoImg(user);
        return true;
    }

    private String addFile(MultipartFile imgFile, String folder) {
        String channelPath = uploadFolder + folder;

        File file = new File(channelPath);
        if(!file.exists()){
            MyFile.mkDirectory(channelPath);
        }
        String oldName = imgFile.getOriginalFilename();
        String newName;
        String img = "";
        if (oldName!=null&&!oldName.equals("")) {
            newName= UUID.randomUUID().toString().replace("-","")
                    +oldName.substring(oldName.lastIndexOf("."));
        } else {
            newName= UUID.randomUUID().toString().replace("-","");
        }
        try {
            imgFile.transferTo(new File(file, Objects.requireNonNull(newName)));
            img = accessPath + folder + "/" + newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    private void removeImg(String oldImg) {
        String replacedPath = oldImg.replaceFirst(accessPath,uploadFolder);
        File fileToDelete = new File(replacedPath);
        if (fileToDelete.exists() && fileToDelete.isFile()) {
            if (fileToDelete.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("The specified file does not exist.");
        }
    }

    public boolean getPassword(String account) {
        String password = userMapper.getPassword(account);
        return password != null && !password.equals("");
    }

    public boolean addFeedback(String account, String info) {
        return userMapper.addFeedback(account,info);
    }

    public List<TrendM> getMyTrendList(String account) {
        List<TrendM> myTrendList = userMapper.getMyTrendList(account);
        for (TrendM trend : myTrendList) {
            trend.setTopicList(communityMapper.getTopicListOfTrend(trend.getId()));
            trend.setUserList(communityMapper.getUserListOfTrend(trend.getId()));
            getImageViewInfoList(trend);
            trend.setHotComment(communityMapper.getHotComment(trend.getId()));
            trend.setLikeStatus(communityMapper.getLike(account, trend.getId()) != null);
            trend.setCollectStatus(communityMapper.getCollect(account, trend.getId()) != null);
            trend.setAttentionStatus(communityMapper.getAttention(trend.getUserAccount(), account) != null);
            switch (trend.getLinkType()){
                case 1:
                    trend.setLinkTypeS("文章笔记");
                    Map<String, String> articleNoteInfo = communityMapper.getArticleNoteInfo(trend.getUserAccount(), trend.getLinkId());
                    if (articleNoteInfo!=null) {
                        if (articleNoteInfo.get("info") != null)
                            trend.setLinkTitle(articleNoteInfo.get("info"));
                        if (articleNoteInfo.get("channelName") != null)
                            trend.setChannelName(articleNoteInfo.get("channelName"));
                        if (articleNoteInfo.get("articleImg") != null)
                            trend.setArticleImg(articleNoteInfo.get("articleImg"));
                    }
                    break;
                case 2:
                    trend.setLinkTypeS("题目笔记");
                    trend.setLinkTitle(communityMapper.getTestNoteInfo(trend.getUserAccount(),trend.getLinkId(),trend.getLinkTable()));
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setSubType("听力");
                            break;
                        case 2:
                            trend.setSubType("选词填空");
                            break;
                        case 3:
                            trend.setSubType("匹配");
                            break;
                        case 4:
                            trend.setSubType("阅读理解");
                            break;
                        case 5:
                            trend.setSubType("翻译");
                            break;
                        case 6:
                            trend.setSubType("写作");
                            break;
                        case 7:
                            trend.setSubType("完形填空");
                            break;
                        case 8:
                            trend.setSubType("新题型");
                            break;
                    }
                    break;
                case 3:
                    trend.setLinkTypeS("文章");
                    trend.setLinkTitle(communityMapper.getArticleTitle(trend.getLinkId()));
                    break;
                case 4:
                    trend.setLinkTypeS("题目");
                    switch (trend.getLinkTable()){
                        case 1:
                            trend.setLinkTitle(communityMapper.getListeningInfo(trend.getLinkId()));
                            break;
                        case 2:
                            trend.setLinkTitle(communityMapper.getBankedListInfo(trend.getLinkId()));
                            break;
                        case 3:
                            trend.setLinkTitle(communityMapper.getMatchListInfo(trend.getLinkId()));
                            break;
                        case 4:
                            trend.setLinkTitle(communityMapper.getCarefulListInfo(trend.getLinkId()));
                            break;
                        case 5:
                            trend.setLinkTitle(communityMapper.getTranslationListInfo(trend.getLinkId()));
                            break;
                        case 6:
                            trend.setLinkTitle(communityMapper.getWritingListInfo(trend.getLinkId()));
                            break;
                        case 7:
                            trend.setLinkTitle(communityMapper.getClozeListInfo(trend.getLinkId()));
                            break;
                        case 8:
                            trend.setLinkTitle(communityMapper.getNewListInfo(trend.getLinkId()));
                            break;
                    }
                    break;
                case 5:
                    trend.setLinkTypeS("动态");
                    trend.setLinkTitle(communityMapper.getTrendInfo(trend.getLinkId()));
                    break;
            }
        }
        return myTrendList;
    }

    private void getImageViewInfoList(TrendM trend) {
        List<ImageViewInfo> imgList = new ArrayList<>();
        List<String> imgs = communityMapper.getImgList(trend.getId());
        for (String img : imgs) {
            ImageViewInfo info = new ImageViewInfo(img);
            imgList.add(info);
        }
        trend.setImgList(imgList);
    }

    public List<User> getMyAttentionList(String account) {
        return userMapper.getMyAttentionList(account);
    }

    public List<User> getMyFanList(String account) {
        return userMapper.getMyFanList(account);
    }
}
