package data.service

import data.util.Uris
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

import javax.inject.Inject

/**
 * @author jleo
 * @date 2021/2/26
 */
class JvmDataService {

  val restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory(){{
    setConnectTimeout(3 * 1000)
    setReadTimeout(3 * 1000)
  }})

  def saveJvmInfo(jvmInfo: String): Unit = {
    try {
      restTemplate.postForObject(Uris.dataUri(Uris.saveJvmInfo), jvmInfo, classOf[Object])
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }
}
