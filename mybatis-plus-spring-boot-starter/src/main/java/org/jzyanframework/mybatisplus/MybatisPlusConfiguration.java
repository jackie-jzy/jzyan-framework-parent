package org.jzyanframework.mybatisplus;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

/**
 * @ProjectName : mybatis-plus
 * @FileName : MybatisPlusConfiguration
 * @Version : 1.0
 * @Description : 自动配置
 * @Author : jzyan
 * @CreateDate : 2020/09/11 10:50
 */
@AutoConfiguration
@ComponentScan("org.jzyanframework.mybatisplus")
@PropertySource("classpath:application-mybatis-plus.properties")
public class MybatisPlusConfiguration {

    // 防止 ali druid 打印警告信息 discard long time none received connection
    static {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

}
