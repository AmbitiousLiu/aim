package com.example.data.web;

import com.example.data.service.TraceInfoService;
import com.example.data.util.CodeResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/2/28
 */
@RestController
@RequestMapping("/trace")
public class TraceInfoController {

    @Autowired
    TraceInfoService traceInfoService;

    @Autowired
    Gson gson;

    @RequestMapping("/save")
    public CodeResult save(@RequestBody String traceInfo) {
        return new CodeResult(traceInfoService.save(traceInfo), null);
    }

    @RequestMapping("/get")
    public CodeResult getJvmInfo(@RequestBody String traceParam) {
        JsonObject jsonObject = gson.fromJson(traceParam, JsonObject.class);
        return new CodeResult(traceInfoService.get(jsonObject.get("name").getAsString(),
                jsonObject.get("begin").getAsString(), jsonObject.get("end").getAsString()));
    }

    @RequestMapping("/getInfoByTraceId")
    public CodeResult getInfoByTraceId(@RequestBody String traceId) {
        return new CodeResult(traceInfoService.getInfoByTraceId(traceId));
    }
}
