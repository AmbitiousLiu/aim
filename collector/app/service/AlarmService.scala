package service

import DTO.{HttpTaskDTO, ShellTaskDTO}
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import data.util.Uris
import play.libs.ws.WSClient

import javax.inject.{Inject, Singleton}

/**
 * @author jleo
 * @date 2021/4/5
 */
@Singleton
class AlarmService @Inject()(ws: WSClient) {
  def verifyValue(value: String, threshold: String): Boolean = {
    val strings = threshold.split("-")
    if (strings.length == 2) {
      if (value.toInt < strings(0).toInt || value.toInt > strings(1).toInt) {
        return false
      }
    }
    true
  }

  def verifyTask(httpTask: HttpTaskDTO, value: String): Unit = {
    if (!verifyValue(value, httpTask.alarmDO.threshold)) {
      if (httpTask.alarmDO.alarmType == 1) {
        sendStationLetter(constructMessage(httpTask, value), httpTask.person)
      }
      if (httpTask.alarmDO.alarmType == 2) {
        sendEmail()
      }
    }
  }

  def verifyTask(shellTask: ShellTaskDTO, value: String): Unit = {
    if (!verifyValue(value, shellTask.alarmDO.threshold)) {
      if (shellTask.alarmDO.alarmType == 1) {
        sendStationLetter(constructMessage(shellTask, value), shellTask.person)
      }
      if (shellTask.alarmDO.alarmType == 2) {
        sendEmail()
      }
    }
  }

  def sendStationLetter(message: String, person: String): Unit = {
    ws.url(Uris.baseWebUri + Uris.pushAlarm(person))
      .post(message)
      .thenApplyAsync(response => {
        return Ok
      })
  }

  def sendEmail(): Unit = {

  }

  def constructMessage(httpTaskDTO: HttpTaskDTO, value: String): String = {
    s"""监控点：${httpTaskDTO.id} 的采集结果：$value
       |超过阈值：${httpTaskDTO.alarmDO.threshold}
       |负责人：${httpTaskDTO.person}
       |请及时处理。
       |""".stripMargin
  }

  def constructMessage(shellTaskDTO: ShellTaskDTO, value: String): String = {
    s"""监控点：${shellTaskDTO.id} 的采集结果：$value
       |超过阈值：${shellTaskDTO.alarmDO.threshold}
       |负责人：${shellTaskDTO.person}
       |请及时处理。
       |""".stripMargin
  }
}
