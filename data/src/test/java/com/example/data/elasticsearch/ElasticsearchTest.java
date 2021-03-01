package com.example.data.elasticsearch;

import com.example.data.elasticsearch.DO.JvmInfo;
import com.example.data.elasticsearch.dao.JvmInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * @author jleo
 * @date 2021/2/25
 */
@SpringBootTest
class ElasticsearchTest {

    @Autowired
    JvmInfoRepository elasticsearch;

    @Autowired
    Elasticsearch elasticsearch1;

    @Test
    void put() throws IOException {

    }

    @Test
    void getAllIndex() {
        List all = elasticsearch.findByServerNameEqualsAndIdBetween("jleo-deepin", "1614339009999", "1614339009999");
        System.out.println(all);
    }

    @Test
    void getAll() {
        Iterable<JvmInfo> all = elasticsearch.findAll();
        System.out.println(all);
    }

    @Test
    void deleteIndex() {
        elasticsearch1.deleteIndex(JvmInfo.class);
    }
}