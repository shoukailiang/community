package com.shoukailiang.community;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/7/31 22:00
 */
@EnableDiscoveryClient
@EnableFeignClients
@EnableSwagger2Doc
@SpringBootApplication
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}