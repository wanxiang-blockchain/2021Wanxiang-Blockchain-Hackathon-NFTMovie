/**
 * @(#)InterceptorConfig.java, 8月 12, 2021.
 * <p>
 * Copyright 2021.
 */
package com.pku.ss.movienftserver.config;

import com.pku.ss.movienftserver.utils.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhangyan
 */


@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    // 拦截所有请求，通过判断是否有 @LoginRequired 注解 决定是否需要登录
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","**/swagger-ui.html")
        .excludePathPatterns("/**");
    }
    @Bean
    public AuthenticationInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor();
    }
}
