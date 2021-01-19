package com.example.aim.agent.bootstrap.asm.registry.cm;

import com.example.aim.agent.bootstrap.asm.registry.ClassRegister;
import com.example.aim.agent.bootstrap.asm.registry.MethodRegister;

/**
 * 类方法的增强
 */
public interface ClassMethodRegister extends ClassRegister {

    /**
     * 为类注册表增加方法注册器
     * @param methodRegister
     */
    void addMethodRegister(MethodRegister methodRegister);
}
