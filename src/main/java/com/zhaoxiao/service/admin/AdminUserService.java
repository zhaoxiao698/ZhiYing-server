package com.zhaoxiao.service.admin;

import com.zhaoxiao.mapper.admin.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
}
