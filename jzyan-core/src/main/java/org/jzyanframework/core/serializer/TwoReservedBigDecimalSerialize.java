package org.jzyanframework.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @FileName : TwoReservedBigDecimalSerialize
 * @Version : 1.0
 * @Description : 自定义 BigDecimal处理 保留两位小数
 * 使用示例 @JsonSerialize(using = TwoReservedBigDecimalSerialize.class)
 * @Author : jzyan
 * @CreateDate : 2020/04/10 14:28
 */
public class TwoReservedBigDecimalSerialize extends JsonSerializer<BigDecimal> {
    /**
     * Method that can be called to ask implementation to serialize
     * values of type this serializer handles.
     *
     * @param value       Value to serialize; can <b>not</b> be null.
     * @param gen         Generator used to output resulting Json content
     * @param serializers Provider that can be used to get serializers for
     */
    @Override
    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value != null) {
            gen.writeObject(value.setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }
}
