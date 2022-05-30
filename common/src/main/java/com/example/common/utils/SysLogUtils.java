package com.example.common.utils;

import com.example.common.service.log.SysLog;
import com.example.common.enums.LogTypeEnum;
import lombok.experimental.UtilityClass;

import java.util.Objects;

/**
 * 系统日志工具类
 */
@UtilityClass
public class SysLogUtils {

    public SysLog getSysLog() {
//        HttpServletRequest request = ((ServletRequestAttributes) Objects
//                .requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
//        SysLog sysLog = new SysLog();
//        sysLog.setCreator(Objects.requireNonNull(getUsername()));
//        sysLog.setType(LogTypeEnum.NORMAL.getType());
//        sysLog.setRemoteAddr(ServletUtil.getClientIP(request));
//        sysLog.setRequestUri(URLUtil.getPath(request.getRequestURI()));
//        sysLog.setMethod(request.getMethod());
//        sysLog.setUserAgent(request.getHeader("user-agent"));
//        sysLog.setParams(HttpUtil.toParams(request.getParameterMap()));
//        sysLog.setServiceId(getClientId());


        SysLog sysLog = new SysLog();
        sysLog.setCreator(Objects.requireNonNull(getUsername()));
        sysLog.setType(LogTypeEnum.NORMAL.getType());
        sysLog.setRemoteAddr("");
        sysLog.setRequestUri("");
        sysLog.setMethod("");
        sysLog.setUserAgent("");
        sysLog.setParams("");
        sysLog.setServiceId(getClientId());

        return sysLog;
    }

    /**
     * 获取客户端
     *
     * @return clientId
     */
    private String getClientId() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication instanceof OAuth2Authentication) {
//			OAuth2Authentication auth2Authentication = (OAuth2Authentication) authentication;
//			return auth2Authentication.getOAuth2Request().getClientId();
//		}
//		return null;
        return "";
    }

    /**
     * 获取用户名称
     *
     * @return username
     */
    private String getUsername() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null) {
//			return null;
//		}
//		return authentication.getName();
        return "";
    }

}