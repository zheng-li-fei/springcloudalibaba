package com.zlf.commonbase.model;

import lombok.*;

/**
 * @author yangxingyao
 * @date 2020/8/5 下午8:14
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUser {

    PhpUser user;

    String access_token;
    //时间戳 单位:秒
    Long sign_time;
    //时间戳 单位:秒
    Long refresh_time;
    //redis key 有效期(默认8小时)
    Long ttl = 28800L;
}

