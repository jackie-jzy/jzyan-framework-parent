package org.jzyanframework.core.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 签名验证
 * </p>
 *
 * @author jzyan
 * @since 2022-06-14
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SignVerify {

    /**
     * 密钥 key
     */
    String value() default "";

}
