package com.zhaoxiao.service.admin;

import com.zhaoxiao.mapper.admin.AdminStudyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminStudyService {
    @Autowired
    private AdminStudyMapper adminStudyMapper;
}
