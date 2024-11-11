package org.jzyanframework.interceptor.context;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.apache.commons.lang3.StringUtils;
import org.jzyanframework.core.constant.AuthConstant;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @FileName : AuthContext
 * @Version : 1.0
 * @Description : session 上下文
 * @Author : jzyan
 * @CreateDate : 2020/11/23 18:34
 */
public class AuthContext {
    private static final TransmittableThreadLocal<AuthContext> LOCAL = TransmittableThreadLocal.withInitial(() -> new AuthContext());
    private final Map<String, String> attachments = new ConcurrentHashMap<>();

    /**
     * get context.
     *
     * @return context
     */
    public static AuthContext getContext() {
        return LOCAL.get();
    }

    /**
     * 清除 context
     */
    public static void removeContext() {
        LOCAL.remove();
    }

    /**
     * get NickName 登录用户昵称
     *
     * @return
     */
    public static String getNickname() {
        return getContext().getAttachment(AuthConstant.NICK_NAME);
    }

    /**
     * get UserName 登录用户账号
     *
     * @return
     */
    public static String getUsername() {
        return getContext().getAttachment(AuthConstant.USER_NAME);

    }

    /**
     * get UserId 登录用户账号id
     *
     * @return
     */
    public static Long getUserId() {
        String userId = getContext().getAttachment(AuthConstant.USER_ID);
        if (StringUtils.isNotBlank(userId)) {
            return Long.valueOf(userId);
        } else {
            return null;
        }
    }

    /**
     * get TId
     *
     * @return
     */
    public static String getTId() {
        return getContext().getAttachment(AuthConstant.T_ID);
    }

    /**
     * get OrgId 登录用户部门id
     *
     * @return
     */
    public static Long getOrgId() {
        String orgId = getContext().getAttachment(AuthConstant.ORG_ID);
        if (StringUtils.isNotBlank(orgId)) {
            return Long.valueOf(orgId);
        } else {
            return null;
        }
    }

    /**
     * get TenantId 登录用户租户id
     *
     * @return
     */
    public static Long getTenantId() {
        String tenantId = getContext().getAttachment(AuthConstant.TENANT_ID);
        if (StringUtils.isNotBlank(tenantId)) {
            return Long.valueOf(tenantId);
        } else {
            return null;
        }
    }

    /**
     * 登录用户账号是否超级用户
     *
     * @return
     */
    public static Boolean isRoot() {
        String userRoot = getContext().getAttachment(AuthConstant.USER_ROOT);
        if (StringUtils.isNotBlank(userRoot)) {
            return Boolean.valueOf(userRoot);
        } else {
            return false;
        }
    }

    /**
     * get UserRoot 登录用户账号是否超级用户
     *
     * @return
     */
    public static Integer getUserRoot() {
        String userRoot = getContext().getAttachment(AuthConstant.USER_ROOT);
        if (StringUtils.isNotBlank(userRoot)) {
            return Integer.valueOf(userRoot);
        } else {
            return null;
        }
    }

    /**
     * get attachment.
     *
     * @param key
     * @return attachment
     */
    public String getAttachment(String key) {
        return attachments.get(key);
    }

    /**
     * set attachment.
     *
     * @param key
     * @param value
     * @return context
     */
    public AuthContext setAttachment(String key, String value) {
        if (value == null) {
            attachments.remove(key);
        } else {
            attachments.put(key, value);
        }
        return this;
    }

    /**
     * remove attachment.
     *
     * @param key
     * @return context
     */
    public AuthContext removeAttachment(String key) {
        attachments.remove(key);
        return this;
    }

}
