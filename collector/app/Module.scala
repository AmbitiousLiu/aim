import com.google.inject.AbstractModule
import message.{JvmReceiver, Kafka, TraceReceiver}

import javax.inject.Inject

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.

 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
class Module extends AbstractModule {

  override def configure(): Unit = {
    val traceReceiver = new TraceReceiver()
    traceReceiver.start()
    val jvmReceiver = new JvmReceiver()
    jvmReceiver.start()

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override  def  run(): Unit = {
        traceReceiver.consumer.close()
        jvmReceiver.consumer.close()
      }
    })
  }

}
