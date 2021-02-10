package com.shoukailiang.community;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EnableSwagger2Doc // 开启swagger功能
@SpringBootApplication
public class QuestionApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionApplication.class,args);
    }
}
