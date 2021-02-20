package com.jleo.jcontrol.access;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jleo
 * @date 2021/2/16
 */
public class RootInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 允许前后端分离项目中的跨域
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        if (response.getHeader("Access-Control-Allow-Credentials") == null) {
            response.addHeader("Access-Control-Allow-Credentials", "true");
        }
        if ("OPTIONS".equals(request.getMethod().toUpperCase())) {
            response.addHeader("Access-Control-Allow-Methods", "GET,HEAD,POST,PUT,DELETE,TRACE,OPTIONS,PATCH");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Accept");
        }
        return true;
    }
}
