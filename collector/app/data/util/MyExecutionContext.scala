package data.util

import akka.actor.ActorSystem
import play.api.libs.concurrent.CustomExecutionContext

import javax.inject.{Inject, Singleton}
import scala.concurrent.ExecutionContext

/**
 * @author jleo
 * @date 2021/3/28
 */
@Singleton
class MyExecutionContext @Inject() (system: ActorSystem)
  extends CustomExecutionContext(system, "my.executor")
    with ExecutionContext
