package com.example.aim.agent.bootstrap;

import com.example.aim.agent.bootstrap.expand.DefaultTraceAdvice;

import java.lang.instrument.Instrumentation;

public class Bootstrap {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("[aim]start premain");

        // add more here
        new DefaultTraceAdvice().registerAgent(inst);

        // jvm
//        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                JvmStack.printMemoryInfo();
//                JvmStack.printGCInfo();
//                System.out.println("===================================================================================================");
//            }
//        }, 0, 5000, TimeUnit.MILLISECONDS);

    }
}
