package data.util

import java.net.URI

/**
 * @author jleo
 * @date 2021/2/26
 */
object Uris {
  val baseDataUri = "http://localhost:8090"
  val baseWebUri = "http://localhost:8000"

  val saveJvmInfo = "/jvm/save"
  val saveTraceInfo = "/trace/save"

  val saveTaskResult = "/task/saveResult"

  def pushAlarm(userId: String) = s"/push/$userId"

  def dataUri(url: String): URI = {
    new URI(baseDataUri + url)
  }
  def webUri(url: String): URI = {
    new URI(baseWebUri + url)
  }
}
