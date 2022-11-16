package com.zlf.serverapi.config;

import lombok.Data;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自定义配置文件
 * @author: zhenglifei
 * @create: 2022/10/9 15:21
 **/
@Data
@RefreshScope
@Configuration
public class GlobalEnvironmentConfig {


}
