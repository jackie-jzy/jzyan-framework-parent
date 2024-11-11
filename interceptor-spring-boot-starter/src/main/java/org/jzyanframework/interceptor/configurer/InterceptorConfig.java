package org.jzyanframework.interceptor.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Version : 1.0
 * @Description : 拦截器
 * @Author : jzyan
 * @CreateDate : 2020/11/23 18:09
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 这个方法用来注册拦截器，我们自己写好的拦截器需要通过这里添加注册才能生效
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
    }

}
