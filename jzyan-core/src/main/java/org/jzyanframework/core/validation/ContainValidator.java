package org.jzyanframework.core.validation;

import org.jzyanframework.core.annotation.valid.Contains;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContainValidator implements ConstraintValidator<Contains, Object> {

    /** 错误提示信息 */
    private String contains;

    /**
     * 初始化方法， 在(懒加载)创建一个当前类实例后，会马上执行此方法
     *
     * 注: 此方法只会执行一次，即:创建实例后马上执行。
     *
     * @param constraintAnnotation
     *         注解信息模型，可以从该模型中获取注解类中定义的一些信息，如默认值等
     * @date 2020-09-07
     */
    @Override
    public void initialize(Contains constraintAnnotation) {
        this.contains = constraintAnnotation.contains();
    }

    /**
     * 校验方法， 每个需要校验的请求都会走这个方法
     *
     * 注: 此方法可能会并发执行，需要根据实际情况看否是需要保证线程安全。
     *
     * @param value 被校验的对象
     * @param context 上下文
     * @return 校验是否通过
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value instanceof String) {
            String strMessage = (String) value;
            return strMessage.contains(contains);
        } else if (value instanceof Integer) {
            return contains.contains(String.valueOf(value));
        }
        return false;
    }
}
