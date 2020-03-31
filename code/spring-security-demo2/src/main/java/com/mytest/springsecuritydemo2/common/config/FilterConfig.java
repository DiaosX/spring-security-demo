package com.mytest.springsecuritydemo2.common.config;

import com.mytest.springsecuritydemo2.common.sercurity.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

//@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthenticationFilter> tokenFilterRegistration() {

        FilterRegistrationBean<JwtAuthenticationFilter> registration = new FilterRegistrationBean<>();

        registration.setFilter(new JwtAuthenticationFilter());
        registration.addUrlPatterns("/*");

        //此处添加需要排除的链接
        List<String> excludePathPatternsList = new ArrayList<>();

        //api文档例外
        excludePathPatternsList.add("/swagger-ui.html");
        excludePathPatternsList.add("/swagger-resources");
        excludePathPatternsList.add("/webjars");
        excludePathPatternsList.add("/v2/api-docs");

        StringBuffer excludePathPatternsBuffer = new StringBuffer();

        for(String str:excludePathPatternsList) {

            excludePathPatternsBuffer.append(str+",");

        }

        String excludePathPatterns = excludePathPatternsBuffer.toString().substring(0, excludePathPatternsBuffer.toString().length()-1);

        registration.addInitParameter("excludePathPatterns", excludePathPatterns);

        registration.setName("tokenFilter");

        registration.setOrder(1);

        return registration;

    }

}
