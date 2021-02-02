package com.jleo.jcontrol.access;

import com.jleo.jcontrol.session.Conversation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jleo
 * @date 2020/12/19
 */
@Aspect
public class PermissionControl {

    @Autowired
    private Conversation conversation;

    @Pointcut("execution(@com.jleo.jcontrol.access.Permission * *.*(..))")
    public void targetMethod() {}

    @Before("targetMethod()")
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("进入JControl的permission拦截before方法");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        conversation.isLogin(request);
    }
}
