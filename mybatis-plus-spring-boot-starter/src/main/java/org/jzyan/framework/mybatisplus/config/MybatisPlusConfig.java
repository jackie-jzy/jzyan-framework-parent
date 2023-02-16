package org.jzyan.framework.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.DataPermissionInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.Setter;
import org.jzyan.framework.mybatisplus.handler.AnnotationDataPermissionHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ProjectName : mybatis-plus
 * @FileName : MybatisPlusConfig
 * @Version : 1.0
 * @Author : jzyan
 * @CreateDate : 2020/06/01 16:04
 */
@Setter
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = {"com.**.mapper", "cn.**.mapper", "org.**.mapper"})
@ConfigurationProperties("mybatis.data.permission")
public class MybatisPlusConfig {

    /**
     * 是否启用 数据权限配置 默认false
     */
    private boolean enabled = false;

    /**
     * 插件配置
     *
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        if (enabled) {
            // 数据权限插件
            DataPermissionInterceptor dataPermissionInterceptor = new DataPermissionInterceptor();
            dataPermissionInterceptor.setDataPermissionHandler(new AnnotationDataPermissionHandler());
            interceptor.addInnerInterceptor(dataPermissionInterceptor);
        }

        // 乐观插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

}
