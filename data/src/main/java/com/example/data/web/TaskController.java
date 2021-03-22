package com.example.data.web;

import com.example.data.hbase.HBase;
import com.example.data.util.CodeResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/3/10
 */
@RestController
public class TaskController {

    @Autowired
    Gson gson;

    @RequestMapping("/task/saveResult")
    public CodeResult saveTaskResult(@RequestBody String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        HBase.insert(HBase.SHELL_TABLE_NAME, jsonObject.get("id").getAsString(), jsonObject.get("time").getAsString(), jsonObject.get("value").getAsString());
        return new CodeResult();
    }

    @RequestMapping("/getCollectedData")
    public CodeResult getCollectedData(@RequestBody String json) {
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        return new CodeResult(HBase.select(HBase.SHELL_TABLE_NAME, jsonObject.get("id").getAsString(), jsonObject.get("begin").getAsString(), jsonObject.get("end").getAsString()));
    }
}
