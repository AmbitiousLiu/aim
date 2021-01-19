package com.example.aim.agent.bootstrap.asm;

import com.example.aim.agent.bootstrap.asm.registry.ClassRegister;

/**
 * 修改器
 * 判断是否需要修改，返回二进制数据
 */
public interface Modifier {

    /**
     * 修改
     * @param className 类名
     * @return 修改后的二进制数据
     */
    byte[] modify(String className);

    void addRegister(ClassRegister register);
}
