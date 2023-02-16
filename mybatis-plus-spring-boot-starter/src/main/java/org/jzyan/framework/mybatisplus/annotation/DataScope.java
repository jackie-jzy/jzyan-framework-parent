package org.jzyan.framework.mybatisplus.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * 数据权限注解
 * </p>
 *
 * @author jzyan
 * @since 2022-03-14
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface DataScope {

    /**
     * 权限列
     *
     * @return
     */
    DataColumn[] value() default {};

}
