package com.example.demo.controller;

import org.springframework.stereotype.Component;

import java.net.UnknownHostException;

/**
 * @author jleo
 * @date 2021/2/3
 */
@Component
public class Service {
    public void get(String name, int i) throws Exception {
        throw new Exception("my exception");
    }

    public void get2(String name) {
        System.out.println(name);
    }
}
