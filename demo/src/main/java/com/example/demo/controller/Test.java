package com.example.demo.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jleo
 * @date 2021/2/3
 */
@RestController
public class Test {
    @Autowired
    Test2 test2;

    @RequestMapping("/test")
    public String test() {
        //System.out.println("test");
        try {
            test2.get("pertest", 1);
            test2.get("pertest", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }
}
