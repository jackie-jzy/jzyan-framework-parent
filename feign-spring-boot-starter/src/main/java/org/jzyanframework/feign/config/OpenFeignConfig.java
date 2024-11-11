package org.jzyanframework.feign.config;

import org.jzyanframework.feign.interceptor.FeignHeaderInterceptor;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * OpenFeignConfig
 * </p>
 *
 * @author jzyan
 * @since 2023-02-16
 */
@Configuration
public class OpenFeignConfig {

    @Bean
    public FeignHeaderInterceptor feignHeaderInterceptor() {
        return new FeignHeaderInterceptor();
    }

    @Bean
    public Logger.Level loggerLevel() {
        return Logger.Level.FULL;
    }

}
