package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author web
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    /**
     * 静态资源处理，即设置静态资源的位置
     * addResourceHandler：指的是对外暴露的访问路径
     * addResourceLocations：指的是内部文件放置的目录
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将访问/static/** 的路由映射到classpath:/static/ 目录下
        File files = new File("upload");
        String path = files.getAbsolutePath();
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + path + "/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 跨域允许
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
                .maxAge(3600 * 24);
    }

    /**
     * 这是注册拦截器的重写方法，
     * addInterceptor：需要一个实现HandlerInterceptor接口的拦截器实例
     * addPathPatterns：用于设置拦截器的过滤路径规则；addPathPatterns("/**")对所有请求都拦截
     * excludePathPatterns：用于设置不需要拦截的过滤规则
     * 拦截器主要用途：进行用户登录状态的拦截，日志的拦截等。
     * .excludePathPatterns("/test/**")是指设定某些接口不做拦截，“父接口/**”是指所有子接口都不被拦截，可以精确到某一接口。
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor()).addPathPatterns("/**").excludePathPatterns("/test/**","/swagger-ui.html", "/swagger-resources/**","/webjars/**","/","/csrf");
    }
}
