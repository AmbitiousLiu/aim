package com.example.web.controller;

import com.example.web.util.AimWebConstant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jleo.jcontrol.bean.VO.CodeResult;
import com.jleo.jcontrol.boot.JControlConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author jleo
 * @date 2021/2/23
 */
@RestController
public class TraceController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    Gson gson;

    @RequestMapping("/getServerList")
    public CodeResult getServerList() throws URISyntaxException {
        URI serverListUri = new URI(AimWebConstant.IP_COLLECTOR + "/system/getBusinessServer");
        ResponseEntity<String> result = restTemplate.getForEntity(serverListUri, String.class);
        if (result.getStatusCode().is2xxSuccessful()) {
            return new CodeResult(result.getBody());
        }
        return new CodeResult(JControlConstant.CODE_RESULT_ERROR, "获取信息失败");
    }

    @RequestMapping("/getServerInfo")
    public String getServerInfo(@RequestParam("name") String name,
                                                @RequestParam("begin") String begin,
                                                @RequestParam("end") String end) throws URISyntaxException {
        URI serverInfoUri = new URI(AimWebConstant.IP_DATA + "/jvm/get");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("begin", begin);
        jsonObject.addProperty("end", end);
        return gson.toJson(restTemplate.postForEntity(serverInfoUri, gson.toJson(jsonObject), Object.class).getBody());
    }

    @RequestMapping("/getTraceInfo")
    public String getTraceInfo(@RequestParam("name") String name,
                                @RequestParam("begin") String begin,
                                @RequestParam("end") String end) throws URISyntaxException {
        URI serverInfoUri = new URI(AimWebConstant.IP_DATA + "/trace/get");
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("name", name);
        jsonObject.addProperty("begin", begin);
        jsonObject.addProperty("end", end);
        return gson.toJson(restTemplate.postForEntity(serverInfoUri, gson.toJson(jsonObject), Object.class).getBody());
    }

    @RequestMapping("/getInfoByTraceId")
    public String getInfoByTraceId(@RequestParam("traceId") String traceId) throws URISyntaxException {
        URI serverInfoUri = new URI(AimWebConstant.IP_DATA + "/trace/getInfoByTraceId");
        return gson.toJson(restTemplate.postForEntity(serverInfoUri, traceId, Object.class).getBody());
    }
}
