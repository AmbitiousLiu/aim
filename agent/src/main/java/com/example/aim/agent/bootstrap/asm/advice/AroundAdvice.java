package com.example.aim.agent.bootstrap.asm.advice;

/**
 * 环绕增强
 */
public interface AroundAdvice {
    /**
     * 前置增强
     */
    void before();

    /**
     * 后置增强
     */
    void after();
}
