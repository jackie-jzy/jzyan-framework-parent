package org.jzyanframework.web.config;

import org.apache.commons.lang3.StringUtils;
import org.jzyanframework.core.constant.AuthConstant;
import org.jzyanframework.core.utils.TIdUtil;
import org.jzyanframework.interceptor.context.AuthContext;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * Filter配置
 * </p>
 *
 * @author jzyan
 * @since 2023-12-04
 */
@Configuration
public class FilterConfig {

    @Bean
    @ConditionalOnProperty(name = "web.repeatable.enabled", havingValue = "true")
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RepeatableFilter());
        // 添加 所有路径都需要走 这个过滤器
        registration.addUrlPatterns("/*");
        registration.setName("repeatableFilter");
        // 最低级别 最后执行的 过滤器
        registration.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
        return registration;
    }

    @Bean
    public TraceIdRequestLoggingFilter traceIdRequestLoggingFilter() {
        return new TraceIdRequestLoggingFilter();
    }

    class TraceIdRequestLoggingFilter extends AbstractRequestLoggingFilter {

        @Override
        protected void beforeRequest(HttpServletRequest request, String message) {
            String tId = request.getHeader(AuthConstant.T_ID);
            AuthContext authContext = AuthContext.getContext();
            if (StringUtils.isNotEmpty(tId)) {
                authContext.setAttachment(AuthConstant.T_ID, tId);
            } else {
                authContext.setAttachment(AuthConstant.T_ID, TIdUtil.getTId());
            }
            Thread.currentThread().setName(authContext.getAttachment(AuthConstant.T_ID));
        }

        @Override
        protected void afterRequest(HttpServletRequest request, String message) {
            AuthContext.removeContext();
        }
    }

}
