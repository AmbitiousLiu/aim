package com.example.data.elasticsearch.dao;

import com.example.data.elasticsearch.DO.JvmInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jleo
 * @date 2021/2/25
 */
@Repository
public interface JvmInfoRepository extends ElasticsearchRepository<JvmInfo, String> {
    List<JvmInfo> findByServerNameEqualsAndIdBetween(String name, String begin, String end);
}
