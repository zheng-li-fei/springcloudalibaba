package com.zlf.serverauth.enums;

import lombok.Getter;

/**
 * @description: 请求来源平台类型枚举: 平台类型: 10-商城, 20-工行
 * @author: zhenglifei
 * @date: 2023/3/29 15:15
 */
@Getter
public enum PlatformTypeEnum {

    /**
     * 10-商城
     */
    SHOP(10,"商城"),
    /**
     * 20-工行
     */
    ICBC(20,"工行"),
    ;

    private int type;
    private String desc;

    /**
     * 构造器
     * @param type  类型
     * @param desc  描述
     */
    PlatformTypeEnum(int type, String desc){
        this.type = type;
        this.desc = desc;
    }

    /**
     * 校验平台类型是否合法
     *
     * @param type
     * @return
     */
    public static boolean checkType(Integer type) {
        if (type == null) {
            return false;
        }
        for (PlatformTypeEnum typeEnum : PlatformTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取平台类型枚举
     *
     * @param type
     * @return
     */
    public static PlatformTypeEnum getPlatformTypeEnumByType(Integer type) {
        if (type == null) {
            return null;
        }
        for (PlatformTypeEnum typeEnum : PlatformTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum;
            }
        }
        return null;
    }

    /**
     * 根据平台类型获取描述
     * @param type
     * @return
     */
    public static String getDescByType(Integer type){
        if(type == null){
            return "";
        }
        for (PlatformTypeEnum typeEnum : PlatformTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                return typeEnum.getDesc();
            }
        }
        return "";
    }



}
