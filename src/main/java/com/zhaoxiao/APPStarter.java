package com.zhaoxiao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class APPStarter {
    public static void main(String[] args) {
        SpringApplication.run(APPStarter.class,args);
    }
}
