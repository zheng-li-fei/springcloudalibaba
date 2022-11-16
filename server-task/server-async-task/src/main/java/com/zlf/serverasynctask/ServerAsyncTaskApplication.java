package com.zlf.serverasynctask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.TimeZone;

@ComponentScan(basePackages = "com.zlf.*")
@SpringBootApplication
public class ServerAsyncTaskApplication {

    public static void main(String[] args) {
        //设置时区为上海
        TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
        SpringApplication.run(ServerAsyncTaskApplication.class, args);
    }

}
