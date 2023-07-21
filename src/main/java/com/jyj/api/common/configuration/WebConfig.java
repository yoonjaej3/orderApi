package com.jyj.api.common.configuration;

import com.jyj.api.common.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean apiFilter() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new
                FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new CustomFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
