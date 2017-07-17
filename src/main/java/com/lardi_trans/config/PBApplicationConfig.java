package com.lardi_trans.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.lardi_trans.interceptor.ErrorInterceptor;

/**
 * Created by nata on 11.03.2017.
 */


@EnableWebMvc

@Configuration

@ComponentScan({"com.lardi_trans.service", "com.lardi_trans.controller"})
public class PBApplicationConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
              registry.addInterceptor(new ErrorInterceptor());

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("/WEB-INF/css/");
        registry.addResourceHandler("/json/**").addResourceLocations("/WEB-INF/json/");
    }
}
