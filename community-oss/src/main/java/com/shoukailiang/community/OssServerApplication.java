package com.shoukailiang.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shoukailiang
 * @version 1.0
 * @date 2021/8/7 20:09
 */
@EnableFeignClients // 扫描Feign接口
@EnableDiscoveryClient // 标识nacos客户端
@SpringBootApplication
public class OssServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssServerApplication.class);
    }
}
