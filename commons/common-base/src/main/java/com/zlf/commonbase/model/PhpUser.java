package com.zlf.commonbase.model;

import lombok.Data;

@Data
public class PhpUser {
    Long id; //用户id
    Long shop_id; //商城id
    Long distributor_id;
    String mobile;
    String username;
}