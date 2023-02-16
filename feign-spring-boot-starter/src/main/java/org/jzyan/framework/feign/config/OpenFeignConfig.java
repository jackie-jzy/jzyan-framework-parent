package org.jzyan.framework.feign.config;

import feign.Logger;
import org.jzyan.framework.feign.interceptor.FeignHeaderInterceptor;
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
