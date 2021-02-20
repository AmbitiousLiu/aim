package com.example.aim.agent.bootstrap.expand;

import com.example.aim.agent.bootstrap.trace.MethodObject;
import com.example.aim.agent.bootstrap.trace.TraceContext;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class DefaultTraceAdvice implements ExpandAgent {

    @Override
    public void registerAgent(Instrumentation instrumentation) {
        AgentBuilder.Transformer transformer = (builder, typeDescription, classLoader, javaModule) -> {
            builder = builder.visit(
                    Advice.to(DefaultTraceAdvice.class)
                            .on(ElementMatchers.isMethod()
                                    .and(ElementMatchers.any())
                                    .and(ElementMatchers.not(ElementMatchers.nameStartsWith("main")))
                            )
            );
            return builder;
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("com.example.demo.controller")) // 指定需要拦截的类
                .transform(transformer)
                .installOn(instrumentation);
    }

    @Advice.OnMethodEnter()
    public static void enter(@Advice.Origin("#t") String className, @Advice.Origin Method method, @Advice.AllArguments Object[] objects) {
        MethodObject methodObject = new MethodObject();
        methodObject.setMethodName(method.toString());
        methodObject.setAllArguments(objects);
        methodObject.setStartTime(System.currentTimeMillis());
        TraceContext.getCurrentTraceObject().pushMethodObject(methodObject);
    }

    @Advice.OnMethodExit(onThrowable = Throwable.class)
    public static void exit(@Advice.Thrown Throwable thrown) {
        MethodObject methodObject = TraceContext.getCurrentTraceObject().peekMethodObject();
        methodObject.setTimeConsuming(System.currentTimeMillis() - methodObject.getStartTime());
        if (thrown != null) {
            TraceContext.getCurrentTraceObject().peekMethodObject().setThrowable(thrown);
        }
        TraceContext.getCurrentTraceObject().popMethodObject();
        TraceContext.tryClearCurrentContext();
    }

}
