package com.zhaoxiao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * @author java1234_小锋
 * @site www.java1234.com
 * @company 南通小锋网络科技有限公司
 * @create 2021-09-21 10:42
 */
@Configuration
public class Swagger3Config {
    /**
     * 配置swagger的Docket bean
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)  // 指定swagger3.0版本
                .groupName("默认")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhaoxiao.controller.admin"))  // 指定扫描的包  常用方式
                .build();
    }
}