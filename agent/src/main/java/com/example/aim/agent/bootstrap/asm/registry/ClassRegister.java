package com.example.aim.agent.bootstrap.asm.registry;

/**
 * 默认注册器
 */
public interface ClassRegister {

    /**
     * 是否被注册了
     * @param className 类名
     * @return 是否需要修改该类
     */
    boolean isNeed(String className);
}
