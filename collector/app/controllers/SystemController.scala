package controllers

import akka.actor.ActorSystem
import com.google.gson.{Gson, JsonArray}
import play.api.libs.concurrent.CustomExecutionContext
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

/**
 * @author jleo
 * @date 2021/2/20
 */
@Singleton
class SystemController @Inject() (myExecutionContext: ExecutionContext, cc: ControllerComponents, gson: Gson) extends AbstractController(cc) {

  var businessServerMap: Map[String, BusinessServer] = Map[String, BusinessServer]()

  def heartbeat: Action[AnyContent] = Action.async { request =>
    Future{ val businessServer = BusinessServer(gson.fromJson(request.body.asText.get, classOf[BusinessServerInfo]))
      if (businessServerMap.getOrElse(businessServer.serverInfo.name, null) == null) {
        businessServerMap += (businessServer.serverInfo.name -> businessServer)
      } else {
        businessServerMap(businessServer.serverInfo.name).beat()
      }
      Ok("")
    }(myExecutionContext)
  }

  def getBusinessServer: Action[AnyContent] = Action {
    Ok(gson.toJson(businessServerMap.values.toArray))
  }
}

case class BusinessServerInfo(name: String,
                              system: String,
                              arch: String,
                              availableProcessors: String) {
}

case class BusinessServer(serverInfo: BusinessServerInfo,
                          var lastTime: Long = System.currentTimeMillis(),
                         ) {
  val normalCode: Int = 0
  val warnCode: Int = 1
  val downCode: Int = -1
  val warn: Long = 30 * 1000
  val down: Long = 60 * 1000

  def detect(): Int = {
    if (System.currentTimeMillis() - lastTime >= down) {
      downCode
    } else if (System.currentTimeMillis() - lastTime >= warn) {
      warnCode
    } else {
      normalCode
    }
  }

  def beat(): Unit = {
    lastTime = System.currentTimeMillis()
  }
}
