package com.example.servicestock;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@MapperScan(value = "com.example.servicestock.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class ServiceStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceStockApplication.class, args);
    }

}
