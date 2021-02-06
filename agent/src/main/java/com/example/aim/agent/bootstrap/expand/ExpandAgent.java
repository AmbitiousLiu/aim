package com.example.aim.agent.bootstrap.expand;

import java.lang.instrument.Instrumentation;

/**
 * @author jleo
 * @date 2021/2/6
 */
public interface ExpandAgent {
    void registerAgent(Instrumentation instrumentation);
}
