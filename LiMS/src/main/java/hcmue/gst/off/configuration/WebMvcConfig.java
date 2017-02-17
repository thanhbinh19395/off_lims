package hcmue.gst.off.configuration;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by Thanh Binh on 2/12/2017.
 */
    @Configuration
    public class WebMvcConfig extends WebMvcConfigurerAdapter {
        @Override
        public void configureDefaultServletHandling(
                DefaultServletHandlerConfigurer configurer) {
            configurer.enable();
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            registry.addResourceHandler("/pagemodule/**").addResourceLocations("classpath:/templates/");
        }
        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new AdminHandlerInterceptor() {
            }).excludePathPatterns("/api/**").addPathPatterns("/Admin/**");
        }

        @Override
        public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
            PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
            resolver.setFallbackPageable(new PageRequest(0, 12));
            argumentResolvers.add(resolver);
            super.addArgumentResolvers(argumentResolvers);
        }

    }

