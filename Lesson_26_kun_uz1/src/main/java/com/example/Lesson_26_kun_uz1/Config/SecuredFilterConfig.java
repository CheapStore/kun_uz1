package com.example.Lesson_26_kun_uz1.Config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecuredFilterConfig {

    @Autowired
    private TokenFilter tokenFilter;

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(tokenFilter);

        bean.addUrlPatterns("/region/adm/*");
        bean.addUrlPatterns("/region/adm");
        bean.addUrlPatterns("/region/adm/**");

        bean.addUrlPatterns("/profile/adm/*");
        bean.addUrlPatterns("/profile/adm");

        bean.addUrlPatterns("/article/adm");
        bean.addUrlPatterns("/article/adm/*");

        bean.addUrlPatterns("/types/adm");
        bean.addUrlPatterns("/types/adm/*");

        bean.addUrlPatterns("/article/moder");
        bean.addUrlPatterns("/article/moder/*");

        bean.addUrlPatterns("/category/adm/*");
        bean.addUrlPatterns("/category/adm");

        bean.addUrlPatterns("/attach/adm/*");
        bean.addUrlPatterns("/attach/adm");


        return bean;
    }


}
