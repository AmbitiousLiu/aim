package message

import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.{Collections, Properties}

/**
 * @author jleo
 * @date 2021/2/7
 */
object Kafka {
  val consumerProperties: Properties = new Properties(){
    put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
    put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, classOf[StringDeserializer].getName)
    put(ConsumerConfig.GROUP_ID_CONFIG, "collector1")
  }

}
