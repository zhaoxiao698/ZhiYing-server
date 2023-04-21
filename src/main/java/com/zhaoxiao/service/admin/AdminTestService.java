package com.zhaoxiao.service.admin;

import com.zhaoxiao.mapper.admin.AdminTestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTestService {
    @Autowired
    private AdminTestMapper adminTestMapper;
}
