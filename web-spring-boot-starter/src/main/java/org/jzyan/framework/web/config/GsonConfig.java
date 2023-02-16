package org.jzyan.framework.web.config;

import cn.hutool.core.date.DatePattern;
import com.google.gson.*;
import org.jzyan.framework.core.IgnoreFieldAnnotationExclusionStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Gson 配置
 * </p>
 *
 * @author jzyan
 * @since 2022-10-08
 */
@Configuration
public class GsonConfig {

    @Bean
    public Gson gson() {
        GsonBuilder builder = new GsonBuilder();
        builder.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        builder.registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter());
        builder.setExclusionStrategies(new IgnoreFieldAnnotationExclusionStrategy());
        return builder.create();
    }

    public class LocalDateAdapter implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        }
    }

}
