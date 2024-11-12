package org.jzyanframework.core.annotation.valid;

import org.jzyanframework.core.validation.PhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义校验注解
 * <p>
 * 校验手机号
 *
 * @author : jzyan
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneValidator.class}) // 指定此注解的实现，即:验证器
public @interface Phone {

    // 当验证不通过时的默认提示信息
    String message() default "Illegal cell phone format!";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

}
