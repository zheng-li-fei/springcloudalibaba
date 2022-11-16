package com.zlf.serverauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@ComponentScan(basePackages = "com.zlf.*")
@SpringBootApplication
public class ServerAuthApplication {

    public static void main(String[] args) {
        //设置时区为上海
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(ServerAuthApplication.class, args);
    }

}
