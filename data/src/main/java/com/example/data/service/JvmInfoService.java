package com.example.data.service;

import com.example.data.elasticsearch.DO.JvmInfo;
import com.example.data.elasticsearch.dao.JvmInfoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jleo
 * @date 2021/2/26
 */
@Service
public class JvmInfoService {

    @Autowired
    JvmInfoRepository jvmInfoRepository;

    @Autowired
    Gson gson;

    public int save(String jvmInfo) {
        JvmInfo jvm = gson.fromJson(jvmInfo, JvmInfo.class);
        jvmInfoRepository.save(jvm);
        return 0;
    }

    public String get(String name, String begin, String end) {
        return gson.toJson(jvmInfoRepository.findByServerNameEqualsAndIdBetween(name, begin, end));
    }
}

