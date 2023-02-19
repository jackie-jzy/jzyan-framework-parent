package org.jzyan.framework.registry;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @Version : 1.0
 * @Description : 自动配置
 * @Author : jzyan
 * @CreateDate : 2020/09/11 10:50
 */
@AutoConfiguration
@ComponentScan("org.jzyan.framework.registry")
@PropertySource("classpath:application-registry.properties")
public class RegistryConfiguration {

}
