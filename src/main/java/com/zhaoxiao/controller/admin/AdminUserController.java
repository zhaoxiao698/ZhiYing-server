package com.zhaoxiao.controller.admin;

import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/admin/user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
}
