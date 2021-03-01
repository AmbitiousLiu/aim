package com.example.data.elasticsearch;

import com.example.data.elasticsearch.DO.JvmInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author jleo
 * @date 2021/2/24
 */
@Component
public class Elasticsearch {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    public void deleteIndex(Class<?> clazz) {
        elasticsearchRestTemplate.indexOps(clazz).delete();
    }

}
