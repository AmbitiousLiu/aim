package com.example.aim.agent.bootstrap;

import com.example.aim.agent.bootstrap.expand.DefaultTraceAdvice;
import com.example.aim.agent.bootstrap.jvm.JvmStack;

import java.lang.instrument.Instrumentation;

public class Bootstrap {

    public static String agentName = "未命名的应用";
    public static String agentPath = "com.example";

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[aim]start premain");
        String[] split = agentArgs.split(",");
        if (split.length != 2) {
            System.out.println("[aim]启动参数配置有误，agent集成失败！");
            return;
        }
        agentName = split[0];
        agentPath = split[1];
        System.out.println("[aim]应用名称：" + agentName);
        System.out.println("[aim]监控路径：" + agentPath);

        // add more here
        new DefaultTraceAdvice().registerAgent(inst);

        // jvm
        JvmStack.jvmTaskStart();

    }
}
