package org.jzyan.framework.interceptor.configurer;

import org.jzyan.framework.core.constant.AuthConstant;
import org.jzyan.framework.interceptor.context.AuthContext;
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
        String userId = request.getHeader(AuthConstant.USER_ID);
        String username = request.getHeader(AuthConstant.USER_NAME);
        String nickname = request.getHeader(AuthConstant.NICK_NAME);
        String userOrgId = request.getHeader(AuthConstant.ORG_ID);
        String userRoot = request.getHeader(AuthConstant.USER_ROOT);
        String tenantId = request.getHeader(AuthConstant.TENANT_ID);
        if (ObjectUtils.isEmpty(userId)) {
            return true;
        }
        AuthContext.getContext()
                .setAttachment(AuthConstant.NICK_NAME, nickname)
                .setAttachment(AuthConstant.USER_ID, userId)
                .setAttachment(AuthConstant.USER_NAME, username)
                .setAttachment(AuthConstant.ORG_ID, userOrgId)
                .setAttachment(AuthConstant.USER_ROOT, userRoot)
                .setAttachment(AuthConstant.TENANT_ID, tenantId);
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
