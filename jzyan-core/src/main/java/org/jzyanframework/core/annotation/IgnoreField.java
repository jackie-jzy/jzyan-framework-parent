package org.jzyanframework.core.annotation;

import java.lang.annotation.*;

/**
 * <p>
 * gson 自定排除策略
 * </p>
 *
 * @author jzyan
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreField {

}
