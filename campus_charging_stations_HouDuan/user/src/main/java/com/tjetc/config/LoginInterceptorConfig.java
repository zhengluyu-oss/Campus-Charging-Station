package com.tjetc.config;

import com.tjetc.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration; // 需要导入这个包
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置类
 */
@Configuration // 1. 必须开启配置注解，Spring 才会加载这个配置
public class LoginInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                // 2. 拦截所有请求
                .addPathPatterns("/user/**")
                // 3. 排除不需要登录的路径
                .excludePathPatterns(
                        "/user/login",
                        "/user/register",
                        "/error",
                        "/image/**",
                        "/video/**",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.html",
                        "/favicon.ico"
                );
    }
}