package com.autohome.frostmourne.monitor.config;

import java.util.List;
import javax.annotation.Resource;

import com.autohome.frostmourne.core.jackson.JacksonObjectMapper;
import com.autohome.frostmourne.monitor.controller.interceptor.PermissionInterceptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author menong
 * @date 2019-03-14
 */
@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

    @Resource
    private PermissionInterceptor permissionInterceptor;

    @Bean
    public PathMatcher antPathMatcher() {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        return matcher;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600 * 24);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html")
                .addResourceLocations("classpath:/dist/index.html")
                .setCacheControl(CacheControl.noCache())
                .setCacheControl(CacheControl.noStore())
                .resourceChain(true);
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/dist/static/", "classpath:/static/")
                .resourceChain(true);
        registry.addResourceHandler("/dist/**")
                .addResourceLocations("classpath:/dist/")
                .resourceChain(true);
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/dist/favicon.ico")
                .resourceChain(true);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/**/*.view").setViewName("forward:/dist/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(permissionInterceptor).addPathPatterns("/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper objectMapper = JacksonObjectMapper.getCommonObjectMapper();
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(0, jackson2HttpMessageConverter);
    }
}
