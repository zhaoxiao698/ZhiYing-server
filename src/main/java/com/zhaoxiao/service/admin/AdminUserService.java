package com.zhaoxiao.service.admin;

import com.zhaoxiao.entity.mine.User;
import com.zhaoxiao.mapper.admin.AdminUserMapper;
import com.zhaoxiao.model.mine.AddAdmin;
import com.zhaoxiao.model.mine.AdminAccount;
import com.zhaoxiao.model.mine.AdminLogin;
import com.zhaoxiao.model.mine.Feedback;
import com.zhaoxiao.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

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
}
