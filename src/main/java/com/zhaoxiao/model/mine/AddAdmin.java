package com.zhaoxiao.model.mine;

public class AddAdmin {
    private boolean addAdmin;
    private String account;
    private String password;

    public AddAdmin() {
    }

    public AddAdmin(boolean addAdmin, String account, String password) {
        this.addAdmin = addAdmin;
        this.account = account;
        this.password = password;
    }

    public boolean isAddAdmin() {
        return addAdmin;
    }

    public void setAddAdmin(boolean addAdmin) {
        this.addAdmin = addAdmin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
