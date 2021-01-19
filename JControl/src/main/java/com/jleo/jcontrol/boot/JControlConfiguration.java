package com.jleo.jcontrol.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(JControlProperties.class)
public class JControlConfiguration {

    @Autowired
    private JControlProperties jControlProperties;

    @Bean
    public String get() {
        return jControlProperties.getPageUrl();
    }

}
