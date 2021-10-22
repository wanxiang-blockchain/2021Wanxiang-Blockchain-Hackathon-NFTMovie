/**
 * @(#)WebConfig.java, 8月 27, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftstorage.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangyan
 */
@Configuration
@EnableSwagger2
public class WebConfig implements WebMvcConfigurer {

    @Value("${fileStorage.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/**").addResourceLocations("file:" + path);
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");// 映射swagger2，如果你继承了swagger2，那么就需要为他也做一个映射，不然无法访问到swagger文档
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}