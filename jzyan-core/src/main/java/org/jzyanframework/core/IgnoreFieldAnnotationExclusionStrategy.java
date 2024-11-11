package org.jzyanframework.core;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import org.jzyanframework.core.annotation.IgnoreField;

/**
 * <p>
 * gson 自定排除策略
 * </p>
 *
 * @author jzyan
 * @since 2022-10-08
 */
public class IgnoreFieldAnnotationExclusionStrategy implements ExclusionStrategy {

    /**
     * @param f the field object that is under test
     * @return true if the field should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(IgnoreField.class) != null;
    }

    /**
     * @param clazz the class object that is under test
     * @return true if the class should be ignored; otherwise false
     */
    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return clazz.isAnnotationPresent(IgnoreField.class);
    }

}
