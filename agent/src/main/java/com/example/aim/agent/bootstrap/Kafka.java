package com.example.aim.agent.bootstrap;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author jleo
 * @date 2021/2/7
 */
public class Kafka {
    public static Producer<String, String> producer = new KafkaProducer<>(
            new Properties(){{
                put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

                // 所有副本都必须应答后再发送
                put(ProducerConfig.ACKS_CONFIG, "all");

                // 发送失败后,再重复发送的次数
                put(ProducerConfig.RETRIES_CONFIG, 0);

                // 一批消息处理大小
                put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

                // 请求时间间隔
                put(ProducerConfig.LINGER_MS_CONFIG, 1);

                // 发送缓存区内存大小
                put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

                // key序列化
                put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

                // value序列化
                put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            }}
    );

    public static String KAFKA_TOPIC_TRACE = "trace";
    public static String KAFKA_TOPIC_JVM = "jvm";

    static {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Kafka.producer.close();
            }
        });
    }
}
