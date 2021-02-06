package com.example.aim.agent.bootstrap.trace;

import java.util.UUID;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class TraceContext {

    private static final ThreadLocal<TraceObject> threadLocal = new ThreadLocal<TraceObject>() {
        @Override
        protected TraceObject initialValue() {
            return new TraceObject(){{
                setTraceId(UUID.randomUUID().toString());
            }};
        }
    };

    public static TraceObject getCurrentTraceObject() {
        return threadLocal.get();
    }

    // called at the end of the trace
    public static void tryClearCurrentContext() {
        if (getCurrentTraceObject().stack.empty()) {
            TraceObject traceObject = threadLocal.get();
            threadLocal.remove();
        }
    }
}
