package com.example.web;

import com.jleo.jcontrol.boot.EnableJControl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJControl // 开启权限控制
@MapperScan("com.example.web.mapper")
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate(new SimpleClientHttpRequestFactory(){{
			setConnectTimeout(60 * 1000);
			setReadTimeout(60 * 1000);
		}});
	}

}
