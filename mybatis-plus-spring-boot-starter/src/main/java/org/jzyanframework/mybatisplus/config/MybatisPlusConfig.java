package org.jzyanframework.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.jzyanframework.mybatisplus.config.properties.PaginationInnerProperties;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Version : 1.0
 * @Author : jzyan
 * @CreateDate : 2020/06/01 16:04
 */
@Setter
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties({PaginationInnerProperties.class})
public class MybatisPlusConfig {
    @Autowired
    private PaginationInnerProperties paginationInner;

    /**
     * 插件配置
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(paginationInner.dbType));
        return interceptor;
    }

}
