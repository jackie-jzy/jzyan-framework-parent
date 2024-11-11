package org.jzyanframework.feign.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.jzyanframework.core.constant.AuthConstant;
import org.jzyanframework.interceptor.context.AuthContext;
import org.springframework.util.ObjectUtils;

/**
 * <p>
 * 拦截器
 * </p>
 *
 * @author jzyan
 * @since 2023-02-16
 */
@Slf4j
public class FeignHeaderInterceptor implements RequestInterceptor {

    /**
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        template.header(AuthConstant.T_ID, toString(AuthContext.getTId()));
        template.header(AuthConstant.USER_ID, toString(AuthContext.getUserId()));
        template.header(AuthConstant.USER_NAME, toString(AuthContext.getUsername()));
    }

    private String toString(Object obj) {
        if (ObjectUtils.isEmpty(obj)) {
            return "";
        } else {
            return obj.toString();
        }
    }

}
