package com.example.aim.agent.bootstrap;


import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

class Transform implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader l, String className, Class<?> c,
                            ProtectionDomain pd, byte[] b) {
//        if (!className.equals("com/example/aim/Test")) {
//            return null;
//        }
//        try {
//            System.out.println("true1");
//            ClassReader classReader = new ClassReader("com/example/aim/Test");
//            System.out.println("true2");
//            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//            System.out.println("true3");
//            //DefaultVisitor defaultVisitor = new DefaultVisitor(classWriter);
//            System.out.println("true4");
//           // classReader.accept(defaultVisitor, ClassReader.EXPAND_FRAMES);
//            System.out.println("true5");
//            return classWriter.toByteArray();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
        return null;
    }

}
