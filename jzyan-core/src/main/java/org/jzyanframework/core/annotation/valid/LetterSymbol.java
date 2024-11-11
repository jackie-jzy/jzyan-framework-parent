package org.jzyanframework.core.annotation.valid;

import org.jzyanframework.core.validation.LetterSymbolValidator;

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
 * 注：字母+下划线 （必须以字母开头以字母结束）
 *
 * @author : jzyan
 * @date : 2020/11/27
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {LetterSymbolValidator.class}) // 指定此注解的实现，即:验证器
public @interface LetterSymbol {

    // 当验证不通过时的默认提示信息
    String message() default "Illegal format!";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 负载
    Class<? extends Payload>[] payload() default {};

}
