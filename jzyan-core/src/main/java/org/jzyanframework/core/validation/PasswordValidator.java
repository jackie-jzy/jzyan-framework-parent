package org.jzyanframework.core.validation;

import org.jzyanframework.core.annotation.valid.Password;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final Pattern PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[~!@#$%^&*.?])[A-Za-z0-9~!@#$%^&*.?]{8,20}$");

    /**
     * 初始化方法， 在(懒加载)创建一个当前类实例后，会马上执行此方法
     * <p>
     * 注: 此方法只会执行一次，即:创建实例后马上执行。
     *
     * @param constraintAnnotation 注解信息模型，可以从该模型中获取注解类中定义的一些信息，如默认值等
     * @date 2020-09-07
     */
    @Override
    public void initialize(Password constraintAnnotation) {
    }

    /**
     * 校验方法， 每个需要校验的请求都会走这个方法
     * <p>
     * 注: 此方法可能会并发执行，需要根据实际情况看否是需要保证线程安全。
     *
     * @param value   被校验的对象
     * @param context 上下文
     * @return 校验是否通过
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isEmpty(value)) {
            return true;
        } else {
            return PATTERN.matcher(value).matches();
        }
    }
}
