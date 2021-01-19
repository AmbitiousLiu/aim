package com.example.web.controller;

import com.jleo.jcontrol.access.Permission;
import com.jleo.jcontrol.boot.JControlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

@RestController
public class Test {

    @Autowired
    public JControlProperties jControlConfig;

    @Permission
    @RequestMapping("test")
    public String test() {
        System.out.println(DigestUtils.md5DigestAsHex("123".getBytes(StandardCharsets.UTF_8)));
        return "hello";
    }
}
