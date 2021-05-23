package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jleo
 * @date 2021/2/3
 */
@RestController
public class Control {
    @Autowired
    Service service;

    @RequestMapping("/getDemoInfo")
    public String info() {
        try {
            service.get("pertest", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "test";
    }

    @RequestMapping("/getDemoPath")
    public String path() {
        service.get2("/path");
        return "test";
    }
}
