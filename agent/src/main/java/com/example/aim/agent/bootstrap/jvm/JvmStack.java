package com.example.aim.agent.bootstrap.jvm;

import com.example.aim.agent.bootstrap.Kafka;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.lang.management.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class JvmStack {

    private static final long MB = 1048576L;

    public static JsonObject getHeapMemoryUsage() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage headMemory = memory.getHeapMemoryUsage();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("init", headMemory.getInit() / MB + "MB");
        jsonObject.addProperty("max", headMemory.getMax() / MB + "MB");
        jsonObject.addProperty("used", headMemory.getUsed() / MB + "MB");
        jsonObject.addProperty("committed", headMemory.getCommitted() / MB + "MB");
        jsonObject.addProperty("use rate", headMemory.getUsed() * 100 / headMemory.getCommitted() + "%");

        return jsonObject;
    }

    public static JsonObject getNonHeapMemoryUsage() {
        MemoryMXBean memory = ManagementFactory.getMemoryMXBean();
        MemoryUsage nonHeadMemory = memory.getNonHeapMemoryUsage();

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("init", nonHeadMemory.getInit() / MB + "MB");
        jsonObject.addProperty("max", nonHeadMemory.getMax() / MB + "MB");
        jsonObject.addProperty("used", nonHeadMemory.getUsed() / MB + "MB");
        jsonObject.addProperty("committed", nonHeadMemory.getCommitted() / MB + "MB");
        jsonObject.addProperty("use rate", nonHeadMemory.getUsed() * 100 / nonHeadMemory.getCommitted() + "%");

        return jsonObject;
    }

    public static JsonObject getSystemInfo() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        JsonObject jsonObject = new JsonObject();
        try {
            jsonObject.addProperty("name", InetAddress.getLocalHost().getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            jsonObject.addProperty("name", UUID.randomUUID().toString());
        }
        jsonObject.addProperty("system", operatingSystemMXBean.getName());
        jsonObject.addProperty("arch", operatingSystemMXBean.getArch());
        jsonObject.addProperty("availableProcessors", operatingSystemMXBean.getAvailableProcessors());
        return jsonObject;
    }

    public static JsonObject getSystemLoadInfo() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("systemLoadAverage", operatingSystemMXBean.getSystemLoadAverage());
        return jsonObject;
    }

    public static JsonArray getGCInfo() {
        List<GarbageCollectorMXBean> garbages = ManagementFactory.getGarbageCollectorMXBeans();
        JsonArray jsonElements = new JsonArray();
        for (GarbageCollectorMXBean garbage : garbages) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", garbage.getName());
            jsonObject.addProperty("count", garbage.getCollectionCount());
            jsonObject.addProperty("took", garbage.getCollectionTime());

            jsonObject.addProperty("pool name", Arrays.toString(garbage.getMemoryPoolNames()));

            jsonElements.add(jsonObject);
        }
        return jsonElements;
    }

    public static void jvmTaskStart() {
        // send system info once

        // send system other info foreach
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
            public void run() {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("time", String.valueOf(System.currentTimeMillis()));
                jsonObject.add("systemLoadInfo", JvmStack.getSystemLoadInfo());
                jsonObject.add("heapMemoryUsage", JvmStack.getHeapMemoryUsage());
                jsonObject.add("nonHeapMemoryUsage", JvmStack.getNonHeapMemoryUsage());
                jsonObject.add("gcInfo", JvmStack.getGCInfo());
                Kafka.producer.send(new ProducerRecord<String, String>(Kafka.KAFKA_TOPIC_JVM, JvmStack.getSystemInfo().get("name").getAsString(), jsonObject.toString()));
            }
        }, 0, 5000, TimeUnit.MILLISECONDS);
    }

}
