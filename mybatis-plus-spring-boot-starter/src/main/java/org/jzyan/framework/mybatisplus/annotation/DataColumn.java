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
@Repeatable(org.jzyan.framework.mybatisplus.annotation.DataScope.class)
public @interface DataColumn {

    /**
     * 表别名
     *
     * @return
     */
    String alias() default "";

    /**
     * 条件字段
     *
     * @return
     */
    String name();

}
