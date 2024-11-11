package org.jzyanframework.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.jzyanframework.core.annotation.Sensitive;

import java.io.IOException;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * 接口字段脱敏
 * </p>
 *
 * @author jzyan
 * @since 2023-02-23
 */
public class SensitiveSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private Sensitive sensitive;

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String val = value;
        if (sensitive != null) {
            String pattern = sensitive.pattern();
            int groupIndex = sensitive.group();
            String mask = sensitive.mask();
            if (pattern.length() > 0) {
                Pattern pa = Pattern.compile(pattern);
                Matcher matcher = pa.matcher(value);
                if (matcher.matches()) {
                    String group = matcher.group(groupIndex);
                    if (mask.length() > 0 && group.length() > 0) {
                        val = val.replace(group, String.join("", Collections.nCopies(group.length(), mask)));
                    }
                }
            }
        }
        gen.writeObject(val);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property)
            throws JsonMappingException {
        sensitive = property.getAnnotation(Sensitive.class);
        return this;
    }

}
