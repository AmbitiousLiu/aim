package com.jleo.jcontrol.access;

import com.jleo.jcontrol.session.Conversation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jleo
 * @date 2021/1/17
 */
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Autowired
    private Conversation conversation;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入JControl登录拦截");
        return conversation.isLogin(request);
    }
}
