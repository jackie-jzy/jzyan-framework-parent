package org.jzyanframework.mybatisplus.config.properties;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 数据库类型
 * </p>
 *
 * @author jzyan
 * @since 2023-06-06
 */
@Data
@ConfigurationProperties(prefix = "mybatis.data.page")
public class PaginationInnerProperties {
    /**
     * 分页 数据库
     */
    public DbType dbType = DbType.MYSQL;

}
