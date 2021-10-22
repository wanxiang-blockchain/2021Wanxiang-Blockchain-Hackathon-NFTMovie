/**
 * @(#)Swagger2Config.java, 8月 06, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftstorage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangyan
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                // 配置到项目的controller包
                .apis(RequestHandlerSelectors.basePackage("com.pku.ss.movienftstorage.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("MovieNftStorage")
                .description("MovieNftStorage Swagger Interface Comments")
                .contact(new Contact("zhangyan1", "https://github.com/1zhangyan/movie-nft-storage", "zhangyan1dev@163.com"))
                .version("0.0.0").build();
    }

}