package org.jzyanframework.core.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jzyanframework.core.serializer.SensitiveSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 * 接口字段脱敏注解
 * </p>
 *
 * @author jzyan
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
// 该注解必须，不然jackson不会识别该注解
@JacksonAnnotationsInside
// 指定我们需要序列化字段的实现
@JsonSerialize(using = SensitiveSerializer.class)
public @interface Sensitive {
    /**
     * 正则表达式
     *
     * @return
     */
    String pattern() default "";

    /**
     * 正则表达式的第几个分组;该分组将被替换为掩码mask
     *
     * @return
     */
    int group() default 0;

    /**
     * 掩码
     *
     * @return
     */
    String mask() default "*";

    public interface Pattern {
        /**
         * 身份证
         */
        String ID = "(\\w{5})(\\w+)(\\w{4})";
        /**
         * 电话
         */
        String PHONE = "(\\w){3}(\\w+)(\\w{3})";
        /**
         * 姓名
         */
        String NAME = "(\\w){1}(\\w+)";
        /**
         * 私密
         */
        String KEY = "(\\w+)";
    }

}
