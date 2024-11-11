package org.jzyanframework.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Version : 1.0
 * @Description : web 配置
 * @Author : jzyan
 * @CreateDate : 2020/09/17 18:00
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * Configure cross origin requests processing.
     *
     * @param registry
     * @since 4.2
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }

    /**
     * Add handlers to serve static resources such as images, js, and, css
     * files from specific locations under web application root, the classpath,
     * and others.
     *
     * @param registry
     * @see ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/", "/static", "/public");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**/favicon.ico")
                .addResourceLocations(new String[]{"classpath:/META-INF/resources/", "/static", "/public"});
    }
}