package com.jleo.jcontrol.access;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author jleo
 * @date 2020/12/19
 */
@Aspect
@Component
public class PermissionControl {

    @Pointcut("execution(@com.jleo.jcontrol.access.Permission * *.*(..))")
    public void targetMethod() {}

    @Before("targetMethod()")
    public void before(JoinPoint joinPoint) throws Throwable {
        System.out.println("before");
    }
}
