package com.example.common.enums;

import lombok.Getter;

/**
 * @description:
 * @author: zhenglifei
 * @create: 2022/5/16 14:07
 **/
@Getter
public enum LogTypeEnum {

    /**
     * 正常日志类型
     */
    NORMAL("normal", "正常日志"),

    /**
     * 错误日志类型
     */
    ERROR("error", "错误日志");;

    String type;
    String msg;

    LogTypeEnum(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }


}
