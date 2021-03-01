package com.example.data.service;

import com.example.data.elasticsearch.DO.JvmInfo;
import com.example.data.elasticsearch.DO.TraceInfo;
import com.example.data.elasticsearch.dao.TraceInfoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jleo
 * @date 2021/2/28
 */
@Service
public class TraceInfoService {

    @Autowired
    TraceInfoRepository traceInfoRepository;

    @Autowired
    Gson gson;

    public int save(String traceInfo) {
        TraceInfo trace = gson.fromJson(traceInfo, TraceInfo.class);
        traceInfoRepository.save(trace);
        return 0;
    }

    public String get(String name, String begin, String end) {
        return gson.toJson(traceInfoRepository.findByServerNameEqualsAndTimeBetween(name, begin, end));
    }

    public String getInfoByTraceId(String traceId) {
        return gson.toJson(traceInfoRepository.findByTraceId(traceId));
    }
}
