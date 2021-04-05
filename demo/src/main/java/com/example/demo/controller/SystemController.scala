package com.example.demo.controller

import com.google.gson.{Gson, JsonArray}

/**
 * @author jleo
 * @date 2021/2/20
 */
class SystemController {

  val gson: Gson = new Gson()

  var businessServerMap: Map[String, BusinessServer] = Map[String, BusinessServer]()

  def heartbeat(string: String) = {
    val businessServer = BusinessServer(gson.fromJson(string, classOf[BusinessServerInfo]))
    if (businessServerMap.getOrElse(businessServer.serverInfo.name, null) == null) {
      businessServerMap += (businessServer.serverInfo.name -> businessServer)
    } else {
      businessServerMap(businessServer.serverInfo.name).beat()
    }
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
