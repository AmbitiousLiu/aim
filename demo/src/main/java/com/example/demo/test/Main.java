package com.example.demo.test;

import org.springframework.util.StopWatch;

import java.util.*;

/**
 * @author jleo
 * @date 2021/3/2
 */
public class Main {
    public static void main(String[] args) {
        DecomposableHashMap<Integer, String> map = new DecomposableHashMap<>();
        Map<Integer, String> hashMap = new HashMap<>(8);
//
//        map.put(48690, "test");
//        map.put(48754, "test2");
//        System.out.println(map.get(48690));
//        System.out.println(map.get(48754));

        StopWatch stopWatch = new StopWatch();
        Random random = new Random();

        stopWatch.start();
        for (int i = 0; i < 800; i++) {
            hashMap.put(random.nextInt(500), String.valueOf(i));
        }
        stopWatch.stop();

        stopWatch.start();
        for (int i = 0; i < 800; i++) {
            map.put(random.nextInt(500), String.valueOf(i));
        }
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
        //System.out.println(new Object().hashCode() >>> 28);
    }
}
