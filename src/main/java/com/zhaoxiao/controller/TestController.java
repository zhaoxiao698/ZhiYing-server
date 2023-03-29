package com.zhaoxiao.controller;

import com.zhaoxiao.response.BaseResponse;
import com.zhaoxiao.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@BaseResponse
@RequestMapping("/test")
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/testGet")
    public String testGet(int id){
        return "id="+id;
    }
}
