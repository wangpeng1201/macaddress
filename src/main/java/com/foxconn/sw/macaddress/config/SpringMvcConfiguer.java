package com.foxconn.sw.macaddress.config;

import com.foxconn.sw.macaddress.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfiguer implements WebMvcConfigurer {

    /**
     * 默认显示首页
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home/index");
        registry.addViewController("/index.html").setViewName("home/index");
        registry.addViewController("/index").setViewName("home/index");
    }

    /**
     * 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/index", "/index.html", "/doLogin")
                .excludePathPatterns("/asserts/**")
                .excludePathPatterns("/bootstrap-4.5.0-dist/**");
    }

    /**
     * 解决resources下面静态资源无法访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    /* 区域解析器  国际化多语 */
    @Bean
    public LocaleResolver localeResolver() {
        return new LocaleResoverLanguage();
    }
}
