package com.zlf.gatewayshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TimeZone;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayShopApplication {

    public static void main(String[] args) {
        //设置时区为上海
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(GatewayShopApplication.class, args);
    }

}
