package org.jzyanframework.interceptor.configurer;

import org.jzyanframework.core.constant.AuthConstant;
import org.jzyanframework.interceptor.context.AuthContext;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @FileName : AuthInterceptor
 * @Version : 1.0
 * @Description : session 拦截器
 * @Author : jzyan
 * @CreateDate : 2020/11/23 18:07
 */
public class AuthInterceptor implements HandlerInterceptor {

    /**
     * 请求之前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String tId = request.getHeader(AuthConstant.T_ID);
        String userId = request.getHeader(AuthConstant.USER_ID);
        String username = request.getHeader(AuthConstant.USER_NAME);
        if (ObjectUtils.isEmpty(userId)) {
            return true;
        }
        AuthContext.getContext()
                .setAttachment(AuthConstant.T_ID, tId)
                .setAttachment(AuthConstant.USER_ID, userId)
                .setAttachment(AuthConstant.USER_NAME, username);
        return true;
    }

    /**
     * 请求之后
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        AuthContext.removeContext();
    }
}
