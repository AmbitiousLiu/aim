package data.service

import data.util.Uris
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

/**
 * @author jleo
 * @date 2021/2/28
 */
class TraceDataService {
  val restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory(){{
    setConnectTimeout(3 * 1000)
    setReadTimeout(3 * 1000)
  }})

  def saveTraceInfo(traceInfo: String): Unit = {
    try {
      restTemplate.postForObject(Uris.dataUri(Uris.saveTraceInfo), traceInfo, classOf[Object])
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
