package com.zlf.serverauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

//@ComponentScan(basePackages = {"com.zlf.commonbase.*","com.zlf.api.*","com.zlf.commonmysql.*"})
@ComponentScan(basePackages = {"com.zlf.*"})
@MapperScan(basePackages = "com.zlf.serverauth.dao")
@EnableFeignClients(basePackages = "com.zlf.api.*")
@EnableDiscoveryClient
@SpringBootApplication
public class ServerAuthApplication {

    public static void main(String[] args) {
        //设置时区为上海
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(ServerAuthApplication.class, args);
    }

}
