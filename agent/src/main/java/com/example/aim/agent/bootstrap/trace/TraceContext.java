package com.example.aim.agent.bootstrap.trace;

import com.example.aim.agent.bootstrap.Kafka;
import com.example.aim.agent.bootstrap.jvm.JvmStack;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.UUID;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class TraceContext {

    private static final Gson gson = new Gson();

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
            JsonObject systemInfo = JvmStack.getSystemInfo();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("traceId", traceObject.getTraceId());
            jsonObject.addProperty("serverName", systemInfo.get("name").getAsString());
            jsonObject.addProperty("time", traceObject.getRoot().getStartTime().toString());
            jsonObject.addProperty("consume", traceObject.getRoot().getTimeConsuming().toString());
            jsonObject.addProperty("normal", traceObject.isNormal());
            jsonObject.addProperty("traceMessage", gson.toJson(traceObject.getRoot()));
            Kafka.producer.send(new ProducerRecord<String, String>(Kafka.KAFKA_TOPIC_TRACE, traceObject.getTraceId(), jsonObject.toString()));
            threadLocal.remove();
        }
    }
}
