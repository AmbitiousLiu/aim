package com.example.data.elasticsearch.dao;

import com.example.data.elasticsearch.DO.TraceInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jleo
 * @date 2021/2/28
 */
@Repository
public interface TraceInfoRepository extends ElasticsearchRepository<TraceInfo, String> {

    List<TraceInfo> findByServerNameEqualsAndTimeBetween(String serverName, String begin, String end);

    List<TraceInfo> findByTraceId(String traceId);
}
