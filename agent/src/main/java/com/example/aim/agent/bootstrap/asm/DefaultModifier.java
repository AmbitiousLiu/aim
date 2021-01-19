package com.example.aim.agent.bootstrap.asm;

import com.example.aim.agent.bootstrap.asm.registry.ClassRegister;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jleo
 * @date 2020/12/6
 */
public class DefaultModifier implements Modifier {

    List<ClassRegister> classRegisterList = new ArrayList<>(16);

    @Override
    public byte[] modify(String className) {
        classRegisterList.forEach(register -> {
            //register.isNeed(className) ? register.
        });
        return new byte[0];
    }

    @Override
    public void addRegister(ClassRegister register) {
        classRegisterList.add(register);
    }
}
