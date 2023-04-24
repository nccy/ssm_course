package com.hnust.config;

import com.hnust.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author 长夜
 * @date 2023/4/17 9:34
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.hnust.controller")
public class SpringMvcConfig implements WebMvcConfigurer {
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        // 设置文件最大上传大小
        multipartResolver.setMaxUploadSize(5242880);//5MB
        // 设置文件解析的编码，注意：一定要和页面的pageencoding保持一致
        multipartResolver.setDefaultEncoding("utf-8");
        return multipartResolver;
    }
    //视图解析器
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/");//配置前缀
        resolver.setSuffix(".html");//配置后缀
        return resolver;
    }
    //访问静态资源
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    //将拦截器与指定的URL路径关联起来,实现权限登录
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/course/**");
        System.out.println("---------------------------register");
    }

}
