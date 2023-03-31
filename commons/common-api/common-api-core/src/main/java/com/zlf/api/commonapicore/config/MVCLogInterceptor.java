package com.zlf.api.commonapicore.config;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.TreeMap;


/**
 * 打印http日志
 * 依赖 ChannelFilter  RequestWrapper
 *
 * @author zhenglifei
 */
@Slf4j
@Component
public class MVCLogInterceptor implements HandlerInterceptor {
    private static final String POST = "POST";
    private static final String GET = "GET";
    private static final String APPLICATION_JSON = "application/json";

    /**
     * 贯穿http请求整这个生命周期
     */
    public static String REQ_ID = "requestId";
    public static String REQ_START_TIME = "requestStartTime";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String requestId = String.format("%s%s", System.currentTimeMillis() / 1000, RandomUtil.randomNumbers(6));
        MDC.put(REQ_ID, "LOG" + requestId);
        MDC.put(REQ_START_TIME, String.valueOf(System.currentTimeMillis()));
        log.info("MVCLogInterceptor#preHandle  {}", "进入日志拦截器");
        StringBuilder sb = new StringBuilder(1000);
        String url = request.getRequestURL().toString();
        // 参数集合 初始化
        Map<String, Object> paramsMaps = new TreeMap();
        String body = "";
        try {
            String contentType = request.getContentType();
            if (POST.equals(request.getMethod().toUpperCase())
                    && contentType != null && contentType.contains(APPLICATION_JSON)) {
                // 防止流读取一次后就没有了, 所以需要将流继续写出去
                if (request instanceof RequestWrapper) {
                    body = ((RequestWrapper) request).getBody();
                } else {
                    log.info("MVCLogInterceptor#preHandle  {}", "日志取消拦截,防止流丢失");
                    return true;
//                    RequestWrapper requestWrapper = new RequestWrapper(request);
//                    body= requestWrapper.getBody();
                }
                if (StringUtils.isNotBlank(body) && JSON.isValid(body)) {
                    paramsMaps = JSONObject.parseObject(body, TreeMap.class);
                    log.debug("parameterMap:" + paramsMaps.toString());
                } else {
                    paramsMaps.put("body", body);
                }
            } else {
                log.info("MVCLogInterceptor#preHandle  {}", "非POST 或 非application/json 跳过日志拦截");
                return true;
//                Map<String, String[]> parameterMap = request.getParameterMap();
//                if(parameterMap!=null){
//                    Set<Map.Entry<String, String[]>> entries = parameterMap.entrySet();
//                    Iterator<Map.Entry<String, String[]>> iterator = entries.iterator();
//                    while (iterator.hasNext()) {
//                        Map.Entry<String, String[]> next = iterator.next();
//                        paramsMaps.put(next.getKey(), next.getValue()[0]);
//                    }
//                }
            }
        } catch (Exception e) {
            log.error("error 日志拦截器解析异常 原始body {}", body, e);
            paramsMaps.put("body", "参数解析异常-99 " + e.getMessage() + body);
        }
        sb.append("\n-----------------------------------------------------------------------------\n");
        if (!url.contains(".css") && !url.contains(".js") && !url.contains(".png") && !url.contains(".jpg")) {
            //sb.append("访问地址     :  " + url + "\n");
            sb.append("客户ip端口   :  " + request.getRemoteAddr() + ":" + request.getRemotePort() + "\n");
        }
        HandlerMethod h = (HandlerMethod) handler;
        sb.append("URL地址     :  ").append(request.getRequestURL()).append("\n");
        sb.append("请求参数     :  ").append(paramsMaps).append("\n");
        sb.append("请求方式     :  ").append(request.getMethod()).append("\n");
        sb.append("入口名称     :  ").append(h.getBean().getClass().getName()).append("\n");
        sb.append("请求方法     :  ").append(h.getMethod().getName()).append("\n");
        sb.append("-----------------------------------------------------------------------------\n");
        log.info(sb.toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception ex) throws Exception {
        String startTime = MDC.get(REQ_START_TIME);
        long endTime = System.currentTimeMillis();
        long consumeTime = endTime - Long.parseLong(startTime);
        log.info("请求接口结束 {},总耗时[{}]毫秒", httpServletRequest.getRequestURI(), consumeTime);
        MDC.clear();
    }

}