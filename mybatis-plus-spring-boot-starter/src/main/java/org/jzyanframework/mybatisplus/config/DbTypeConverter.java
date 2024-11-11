package org.jzyanframework.mybatisplus.config;

import com.baomidou.mybatisplus.annotation.DbType;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 自定义分页属性枚举转换器
 * </p>
 *
 * @author jzyan
 * @since 2023-11-17
 */
@Component
@ConfigurationPropertiesBinding
public class DbTypeConverter implements Converter<String, DbType> {
    /**
     * Convert the source object of type {@code S} to target type {@code T}.
     *
     * @param dbType the source object to convert, which must be an instance of {@code S} (never {@code null})
     * @return the converted object, which must be an instance of {@code T} (potentially {@code null})
     * @throws IllegalArgumentException if the source cannot be converted to the desired target type
     */
    @Override
    public DbType convert(String dbType) {
        if (ObjectUtils.isEmpty(dbType)) {
            return DbType.MYSQL;
        }
        return DbType.getDbType(dbType);
    }

}
