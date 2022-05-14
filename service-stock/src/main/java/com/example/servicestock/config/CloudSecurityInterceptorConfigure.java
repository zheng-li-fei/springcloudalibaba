package com.example.servicestock.config;

import com.example.servicestock.interceptor.ServerProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CloudSecurityInterceptorConfigure implements WebMvcConfigurer {
 
    private CloudSecurityProperties properties; 
 
    @Autowired
    public void setProperties(CloudSecurityProperties properties) { 
        this.properties = properties; 
    } 
 
    @Bean
    public HandlerInterceptor serverProtectInterceptor() {
        ServerProtectInterceptor interceptor = new ServerProtectInterceptor();
        interceptor.setProperties(properties); 
        return interceptor; 
    } 
 
    @Override 
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(serverProtectInterceptor()); 
    } 
}