package com.zlf.commonbase.constant;

/**
 * 全局公共常量
 *
 * @author zlt
 * @date 2018/10/29
 */
public interface CommonConstants {

    /**
     * token请求头名称
     */
    String AUTHORIZATION = "authorization";

    /**
     * The access token issued by the authorization server. This value is REQUIRED.
     */
    String ACCESS_TOKEN = "access_token";

    String BEARER_TYPE = "Bearer";

    String UNDEFINED = "undefined";

    int LOGIN_ERROR_CODE = 401;


    /**
     * 日志链路追踪id信息头
     */
    String TRACE_ID_HEADER = "x-traceId-header";
    /**
     * 日志链路追踪id日志标志
     */
    String LOG_TRACE_ID = "traceId";

}
