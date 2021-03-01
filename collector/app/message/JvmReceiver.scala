package message

import data.service.JvmDataService
import org.apache.kafka.clients.consumer.KafkaConsumer

import java.util.Collections

/**
 * @author jleo
 * @date 2021/2/7
 */
class JvmReceiver extends Thread {

  val consumer = new KafkaConsumer[String, String](Kafka.consumerProperties)

  val jvmDataService = new JvmDataService()

  override def run(): Unit = {
    print("ok")
    consumer.subscribe(Collections.singletonList("jvm"))
    while (true) {
      var records = consumer.poll(100)
      var iter = records.iterator()
      while (iter.hasNext) {
        val record = iter.next()
        // save to database
        println(record.offset() + "--" + record.key() + "--" + record.value())
        jvmDataService.saveJvmInfo(record.value())
      }
      consumer.commitSync()
    }
  }

}
