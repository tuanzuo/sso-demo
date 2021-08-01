package com.tz.tm.config;

import com.tz.tm.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web配置
 */
@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        /**
         * {@link WebMvcConfigurerAdapter} 已经废弃了，直接使用WebMvcConfigurer v1.5.0
         */
        return new WebMvcConfigurer() {
            //添加拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //设置token验证的Interceptor
                registry.addInterceptor(new AuthInterceptor());
            }
        };
    }

}
