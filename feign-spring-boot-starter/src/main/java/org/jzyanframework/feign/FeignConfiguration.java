package org.jzyanframework.feign;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName : web
 * @FileName : WebConfiguration
 * @Version : 1.0
 * @Description : 自动配置
 * @Author : jzyan
 * @CreateDate : 2020/09/11 10:50
 */
@AutoConfiguration
@ComponentScan("org.jzyanframework.feign")
@PropertySource("classpath:application-feign.properties")
public class FeignConfiguration {

}
