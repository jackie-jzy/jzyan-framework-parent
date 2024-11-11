package org.jzyanframework.web.config;

import cn.hutool.core.date.DatePattern;
import com.google.gson.*;
import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

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
    public ExtendGsonBuilderCustomizer extendGsonBuilderCustomizer() {
        return new ExtendGsonBuilderCustomizer();
    }

    static final class ExtendGsonBuilderCustomizer implements GsonBuilderCustomizer, Ordered {
        ExtendGsonBuilderCustomizer() {
        }

        @Override
        public int getOrder() {
            return -1;
        }

        @Override
        public void customize(GsonBuilder builder) {
            builder.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            builder.registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter());
        }
    }

    static class LocalDateAdapter implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
            return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern(DatePattern.NORM_DATETIME_PATTERN)));
        }
    }

}
