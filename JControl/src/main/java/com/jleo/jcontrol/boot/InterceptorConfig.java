package com.jleo.jcontrol.boot;

import com.jleo.jcontrol.access.PermissionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author jleo
 * @date 2021/1/17
 */
@Component
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JControlProperties jControlProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor()).addPathPatterns(jControlProperties.getInterceptUrl());
    }
}
