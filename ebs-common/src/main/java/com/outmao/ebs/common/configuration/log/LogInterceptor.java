package com.outmao.ebs.common.configuration.log;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *
 * 拦截器
 *
 */
@Slf4j
@Component
public class LogInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        printLog(request);
        return true;
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception {
    }
    
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
    }


    public void printLog(HttpServletRequest request){
        try {
            log.info(
                    "\r\nRequest Begin, URI:{}"
                            +"\r\nRequest Type: {}"
                            +"\r\nProtocol: {}"
                            +"\r\nRequest Body:{}"
                    ,getRequstAddress(request)
                    ,request.getMethod()
                    ,request.getScheme()
                    ,getRequestBody(request)
            );

        } catch (Exception e) {
            log.info("HandlerInterceptor err: "+e.toString());
            e.printStackTrace();
        }
    }


    public String getRequstAddress(HttpServletRequest request){
        String result ="";
        String reqMethod = request.getMethod();
        if("POST".equalsIgnoreCase(reqMethod)){
            result = request.getRequestURL().toString();
            String allPara = getAllPostParam(request);
            if( StringUtils.isNotEmpty(allPara) ){
                result += ("?" + allPara);
            }
        }
        if("GET".equalsIgnoreCase(reqMethod)){
            result = request.getQueryString() != null ?
                    request.getRequestURL().append("?").append(request.getQueryString()).toString() :
                    request.getRequestURL().toString();
        }
        return result;
    }

    private String getRequestBody(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if (contentLength <= 0) {
            return "";
        }
        try {
            return IOUtils.toString(request.getReader());
        } catch (IOException e) {
            log.error("获取请求体失败", e);
            return "";
        }
    }

    private String getAllPostParam(HttpServletRequest request){
        Map<String, String[]> postParam = request.getParameterMap();
        String queryString = "";
        for (String key : postParam.keySet()) {
            String[] values = postParam.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                queryString += key + "=" + value + "&";
            }
        }
        if(StringUtils.isNotEmpty(queryString)){
            queryString = queryString.substring(0, queryString.length() - 1);
        }
        return queryString;
    }

}