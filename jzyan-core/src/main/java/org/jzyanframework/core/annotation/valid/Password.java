package org.jzyanframework.core.annotation.valid;

import org.jzyanframework.core.validation.PasswordValidator;

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
 * 注：校验密码 字母+数字+符号组合8-20位
 *
 * @author jzyan
 * @date 2020-09-07
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PasswordValidator.class}) // 指定此注解的实现，即:验证器
public @interface Password {

    // 当验证不通过时的默认提示信息
    String message() default "Incorrect password rules!";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

}