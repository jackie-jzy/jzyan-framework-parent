package org.jzyanframework.web.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * LocalDateTime 处理器配置
 * </p>
 *
 * @author jzyan
 * @since 2023-02-03
 */
@Slf4j
@Configuration
public class LocalDateTimeSerializerConfig {
    @Value("${spring.jackson.date-format:yyyy-MM-dd HH:mm:ss}")
    private String pattern;

    @Bean
    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public LocalDateTimeDeserializer localDateTimeDeserializer() {
        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.serializerByType(LocalDateTime.class, localDateTimeSerializer());
            builder.deserializerByType(LocalDateTime.class, localDateTimeDeserializer());
            builder.simpleDateFormat(pattern);
        };
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = null;
                try {
                    switch (source.length()) {
                        case 10:
                            log.debug("传过来的是日期格式：{}", source);
                            source = source + " 00:00:00";
                            break;
                        case 13:
                            log.debug("传过来的是日期 小时格式：{}", source);
                            source = source + ":00:00";
                            break;
                        case 16:
                            log.debug("传过来的是日期 小时:分钟格式：{}", source);
                            source = source + ":00";
                            break;
                    }
                    if (!StringUtils.isEmpty(source)) {
                        dateTime = LocalDateTime.parse(source, df);
                    }
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
                return dateTime;
            }
        };
    }
}
