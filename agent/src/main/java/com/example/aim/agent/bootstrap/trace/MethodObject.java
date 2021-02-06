package com.example.aim.agent.bootstrap.trace;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class MethodObject {

    private Method method;

    private Long startTime;

    private Long timeConsuming;

    private Throwable throwable;

    private final List<MethodObject> methodList = new ArrayList<>();

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(Long timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }

    public void addMethodObject(MethodObject methodObject) {
        methodList.add(methodObject);
    }
}
