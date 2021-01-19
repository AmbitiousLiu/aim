package com.example.aim.agent.bootstrap.asm.registry;

import com.example.aim.agent.bootstrap.asm.advice.AroundAdvice;

/**
 * 方法的注册器
 */
public interface MethodRegister {

    /**
     * 是否注册该方法
     * @param methodName 方法名
     * @return 是否需要过滤该方法
     */
    boolean isNeed(String methodName);

    /**
     * 增加环绕增强器
     * @param aroundAdvice 环绕增强器
     */
    void addAdvice(AroundAdvice aroundAdvice);
}
