package service

import DTO.{HttpTaskDTO, ShellTaskDTO}
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import akka.util.LineNumbers.Result
import com.fasterxml.jackson.databind.JsonNode
import data.dao.TaskDao
import data.util.{ShellUtil, Uris}
import jdk.net.SocketFlow.Status
import play.libs.Json
import play.libs.ws.WSClient

import java.util.concurrent.{Executors, ScheduledExecutorService, TimeUnit}
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

/**
 * @author jleo
 * @date 2021/3/8
 */
@Singleton
class TaskService @Inject() (ws: WSClient, taskDao: TaskDao, alarmService: AlarmService)(implicit ec: ExecutionContext) {

  val shellUtil = new ShellUtil()

  def initTask(): Future[Unit] = {
    getTask.map(result => {
      val scheduledExecutorService = Executors.newScheduledThreadPool(1)
      result._1.foreach(shellTask => {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable {
          override def run(): Unit = {
            print(shellTask.id + "begin")
            sendShellResult(shellTask, shellUtil.execute(shellTask.shellTask.ip,
              shellTask.shellTask.port.toInt,
              shellTask.shellTask.userName,
              shellTask.shellTask.password,
              shellTask.shellTask.command)
            )
          }
        }, 0, shellTask.cycle.toInt, TimeUnit.MINUTES)
      })
      result._2.foreach(httpTask => {
        scheduledExecutorService.scheduleAtFixedRate(new Runnable {
          override def run(): Unit = {
            print(httpTask.id + "begin")
            ws.url(httpTask.url).get().thenApplyAsync(response => {
              if ((response.getStatus / 100) == 2) {
                sendHttpResult(httpTask, "0")
              } else {
                sendHttpResult(httpTask, "1")
              }
              return Ok
            })
          }
        }, 0, httpTask.cycle.toInt, TimeUnit.MINUTES)
      })
    })
  }

  def sendResult(json: JsonNode): Unit = {
    ws.url(Uris.baseDataUri + Uris.saveTaskResult)
      .post(json)
      .thenApplyAsync(response => {
        if ((response.getStatus / 100) != 2) {
          sendResult(json)
        }
        return Ok
    })
  }

  def sendShellResult(shellTask: ShellTaskDTO, value: String): Unit = {
    alarmService.verifyTask(shellTask, value)
    val json = Json.newObject()
      .put("id", shellTask.id)
      .put("value", value)
      .put("time", System.currentTimeMillis().toString)
    sendResult(json)
  }

  def sendHttpResult(httpTask: HttpTaskDTO, value: String): Unit = {
    alarmService.verifyTask(httpTask, value)
    val json = Json.newObject()
      .put("id", httpTask.id)
      .put("value", value)
      .put("time", System.currentTimeMillis().toString)
    sendResult(json)
  }

  def getTask: Future[(Seq[ShellTaskDTO], Seq[HttpTaskDTO])] = {
    Future.successful(taskDao.getTask)
  }
}
