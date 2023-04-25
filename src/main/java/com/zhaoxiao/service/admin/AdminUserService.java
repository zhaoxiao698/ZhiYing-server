package com.zhaoxiao.service.admin;

import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.entity.study.Channel;
import com.zhaoxiao.mapper.admin.AdminUserMapper;
import com.zhaoxiao.model.mine.AddAdmin;
import com.zhaoxiao.model.mine.AdminAccount;
import com.zhaoxiao.model.mine.AdminLogin;
import com.zhaoxiao.model.mine.Feedback;
import com.zhaoxiao.util.AccountUtil;
import com.zhaoxiao.util.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    @Value("${file.accessPath}")
    private String accessPath;

    public boolean verifyPermissions(String account, int requirePermissions){
        int permissions = adminUserMapper.selectPermissions(account);
        switch (requirePermissions){
            case 0:
                if (permissions==0||permissions==1||permissions==2){
                    return true;
                }
                break;
            case 1:
                if (permissions==1||permissions==2){
                    return true;
                }
                break;
            case 2:
                if (permissions==2){
                    return true;
                }
                break;
        }
        return false;
    }

    public AdminLogin login(AdminAccount adminAccount) {
        String account = adminAccount.getAccount();
        String password = adminAccount.getPassword();
        AdminLogin adminLogin;
        if(adminUserMapper.selectAccountPassword(account,password)==null){
            adminLogin = new AdminLogin(false, -1, "账号或密码错误");
        }else {
            int permissions = adminUserMapper.selectPermissions(account);
            switch (permissions){
                case 1:
                case 2:
                    adminLogin = new AdminLogin(true, permissions, "登录成功");break;
                default: adminLogin = new AdminLogin(false, permissions, "登录失败，权限不足");break;
            }
        }
        return adminLogin;
    }

    public List<User> getUserList(/*String account*/int permissions) {
/*        if (verifyPermissions(account, 1)) {
            return adminUserMapper.getUserList();
        }
        return null;*/
        return adminUserMapper.getUserList(permissions);
    }

    public boolean resetPassword(String account) {
        return adminUserMapper.resetPassword(account);
    }

    public boolean freezeUser(String account, boolean isFreeze) {
        if (isFreeze){
            return adminUserMapper.freezeUser(account,0);
        } else {
            return adminUserMapper.freezeUser(account,1);
        }
    }

    public AddAdmin addAdmin() {
        String password = "123456";

        //生成账号
        List<String> accountOldList = adminUserMapper.getAccountList();
        List<String> accountNewList = AccountUtil.getUserIds(accountOldList,1,"zy_admin");
        String accountNew = accountNewList.get(0);

        User user = new User();
        user.setAccount(accountNew);
        user.setPassword(password);
        user.setName("用户"+accountNew);
        user.setPermissions(1);
        adminUserMapper.addAdmin(user);

        return new AddAdmin(true,accountNew, password);
    }

    public boolean deleteAdmin(String account) {
        return adminUserMapper.deleteAdmin(account);
    }

    public List<Feedback> getFeedbackList() {
        return adminUserMapper.getFeedbackList();
    }

    public boolean handleFeedback(int id) {
        return adminUserMapper.handleFeedback(id);
    }

    public boolean sendMessage(String receiveAccount, String info) {
        return adminUserMapper.sendMessage(receiveAccount,info);
    }

    public User getUser(String account) {
        return adminUserMapper.getUser(account);
    }

    public boolean setUser(String account,
                           String phone,
                           String name,
                           MultipartFile avatarImg,
                           int age,
                           String sex) {
        String avatar = addFile(avatarImg,"user/avatar");

        //删除原来的文件
        String oldImg = adminUserMapper.getUserAvatar(account);
        if (oldImg!=null){
            removeImg(oldImg);
        }

        User user = new User();
        user.setAccount(account);
        user.setPhone(phone);
        user.setName(name);
        user.setAvatar(avatar);
        user.setAge(age);
        user.setSex(sex);
        return adminUserMapper.setUser(user);
    }

    public boolean setPassword(AdminAccount adminAccount) {
        if (adminAccount.getPassword()==null||adminAccount.getPassword().equals("")) {
            return false;
        }
        return adminUserMapper.setPassword(adminAccount);
    }

    public boolean setUserWithNoImg(String account, String phone, String name, int age, String sex) {
        User user = new User();
        user.setAccount(account);
        user.setPhone(phone);
        user.setName(name);
        user.setAge(age);
        user.setSex(sex);
        return adminUserMapper.setUserWithNoImg(user);
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
}
