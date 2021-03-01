package com.example.data.web;

import com.example.data.service.JvmInfoService;
import com.example.data.util.CodeResult;
import com.example.data.util.Constant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/2/26
 */
@RestController
@RequestMapping("/jvm")
public class JvmInfoController {

    @Autowired
    JvmInfoService jvmInfoService;

    @Autowired
    Gson gson;

    @RequestMapping("/save")
    public CodeResult saveJvmInfo(@RequestBody String jvmInfo) {
        return new CodeResult(jvmInfoService.save(jvmInfo), null);
    }

    @RequestMapping("/get")
    public CodeResult getJvmInfo(@RequestBody String jvmParam) {
        JsonObject jsonObject = gson.fromJson(jvmParam, JsonObject.class);
        return new CodeResult(jvmInfoService.get(jsonObject.get("name").getAsString(),
                jsonObject.get("begin").getAsString(), jsonObject.get("end").getAsString()));
    }
}
