package com.example.aim.agent.bootstrap.jvm;

import com.example.aim.agent.bootstrap.Kafka;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.management.*;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author jleo
 * @date 2021/2/5
 */
public class JvmStack {

    private static final long MB = 1048576L;
    private static URI urlRegister;
    private static URI urlHeartbeat;
    private static RestTemplate restTemplateRegister;
    private static RestTemplate restTemplateHeartbeat;

    static {
        try {
            urlRegister = new URI("http://localhost:9000/system/register");


            restTemplateRegister = new RestTemplate(new SimpleClientHttpRequestFactory(){{
                setConnectTimeout(60 * 1000);
                setReadTimeout(60 * 1000);
            }});

            restTemplateHeartbeat = new RestTemplate(new SimpleClientHttpRequestFactory(){{
                setConnectTimeout(3 * 1000);
                setReadTimeout(3 * 1000);
            }});

            urlHeartbeat = new URI("http://localhost:9000/system/heartbeat");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }




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
        // send register info once
        JsonObject systemInfo = JvmStack.getSystemInfo();
        try {

            ScheduledExecutorService scheduledExecutorService1 = Executors.newScheduledThreadPool(1);
            scheduledExecutorService1.scheduleAtFixedRate(new Runnable() {
                public void run() {
                    // send heart beat foreach
                    try {
                        restTemplateHeartbeat.postForEntity(urlHeartbeat, systemInfo.toString(), Object.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // send system other info foreach
                    /*
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", String.valueOf(System.currentTimeMillis()));
                    jsonObject.addProperty("serverName", systemInfo.get("name").getAsString());
                    jsonObject.addProperty("systemLoadInfo", JvmStack.getSystemLoadInfo().toString());
                    jsonObject.addProperty("heapMemoryUsage", JvmStack.getHeapMemoryUsage().toString());
                    jsonObject.addProperty("nonHeapMemoryUsage", JvmStack.getNonHeapMemoryUsage().toString());
                    jsonObject.addProperty("gcInfo", JvmStack.getGCInfo().toString());
                    System.out.println(jsonObject);
                    Kafka.producer.send(new ProducerRecord<String, String>(Kafka.KAFKA_TOPIC_JVM, systemInfo.get("name").getAsString(), jsonObject.toString()));
                    */
                }
            }, 0, 5000, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
