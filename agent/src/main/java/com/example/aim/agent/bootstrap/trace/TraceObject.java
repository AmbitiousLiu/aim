package com.example.aim.agent.bootstrap.trace;

import java.util.Stack;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class TraceObject {

    String traceId;

    MethodObject root;

    boolean normal = true;

    Stack<MethodObject> stack = new Stack<>();

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public MethodObject getRoot() {
        return root;
    }

    public void setRoot(MethodObject root) {
        this.root = root;
    }

    public boolean isNormal() {
        return normal;
    }

    public void setNormal(boolean normal) {
        this.normal = normal;
    }

    public void pushMethodObject(MethodObject methodObject) {
        if (!stack.empty()) {
            stack.peek().addMethodObject(methodObject);
        } else {
            setRoot(methodObject);
        }
        stack.push(methodObject);
    }

    public MethodObject peekMethodObject() {
        if (!stack.empty()) {
            return stack.peek();
        } else {
            return null;
        }
    }

    public void popMethodObject() {
        if (!stack.empty()) {
            stack.pop();
        }
    }
}
