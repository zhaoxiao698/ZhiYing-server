package com.zhaoxiao.service.admin;

import com.zhaoxiao.mapper.admin.AdminCommunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCommunityService {
    @Autowired
    private AdminCommunityMapper adminCommunityMapper;
}
