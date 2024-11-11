package org.jzyanframework.web.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * RepeatableFilter
 * </p>
 *
 * @author jzyan
 * @since 2023-12-04
 */
public class RepeatableFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        // 判断请求是否属于 HttpServletRequest，并且 Content-type 是否是 "application/json"
        if (request instanceof HttpServletRequest
                && StringUtils.startsWithIgnoreCase(request.getContentType(), MediaType.APPLICATION_JSON_VALUE)) {
            // 创建包装类 RepeatedlyRequestWrapper 封装原生 HttpServletRequest 请求
            // Filter 中将 ServletRequest 替换为 RepeatedlyRequestWrapper
            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, (HttpServletResponse) response);
        }
        if (null == requestWrapper) {
            // 如果为空 --> 使用原生 HttpServletRequest 请求
            chain.doFilter(request, response);
        } else {
            // 如果不为空 -->  使用包装类 RepeatedlyRequestWrapper
            chain.doFilter(requestWrapper, response);
        }
    }
}
