package com.zlf.commonbase.utils;

import com.zlf.commonbase.enums.CommonCodeEnumEx;
import com.zlf.commonbase.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 网关工具
 * @author: zhenglifei
 * @date: 2023/3/30 13:47
 */
@Slf4j
public class GatewayUtil {

    /**
     * 校验网关请求是否合法
     *
     * @param timestamp
     */
    public static void checkGatewayTimestamp(String timestamp) {
        if (StringUtils.isNotBlank(timestamp)) {
            long limitTimeMillis = Long.getLong(timestamp) + 5 * 60 * 1000;
            long currentTimeMillis = System.currentTimeMillis();
            if (limitTimeMillis >= currentTimeMillis) {
                //5分钟内访问
                return;
            }
            log.error("非法访问,limitTimeMillis {},currentTimeMillis {}", limitTimeMillis, currentTimeMillis);
            throw new BizException(CommonCodeEnumEx.GATEWAY_TIMEOUT);
        }
        log.error("非法访问,未获取到网关允许访问标识");
        throw new BizException(CommonCodeEnumEx.GATEWAY_FLAG_LOST);
    }

}
