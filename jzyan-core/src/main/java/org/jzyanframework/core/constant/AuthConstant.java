package org.jzyanframework.core.constant;

/**
 * 认证常量
 *
 * @author jzyan
 */
public class AuthConstant {

    /**
     * 登录用户 账号Header头 key
     */
    public static final String USER_NAME = "User-Name";
    /**
     * 登录用户 昵称Header头 key
     */
    public static final String NICK_NAME = "Nick-Name";
    /**
     * 登录用户 账号id Header头 key
     */
    public static final String USER_ID = "User-Id";
    /**
     * repId
     */
    public static final String T_ID = "T-Id";
    /**
     * 登录用户 组织机构id Header头 key
     */
    public static final String ORG_ID = "Org-Id";
    /**
     * 登录用户 租户id Header头 key
     */
    public static final String TENANT_ID = "Tenant-Id";
    /**
     * 登录用户 是否超级用户 Header头 key
     */
    public static final String USER_ROOT = "User-Root";

    /**
     * redis key
     */
    public static final String AUTH_RESOURCE_ROLES_MAP = "AUTH:RESOURCE_ROLES_MAP";
    /**
     * redis key
     */
    public static final String AUTH_TOKEN = "AUTH:TOKEN:";
    /**
     * redis key
     */
    public static final String AUTH_CODE = "AUTH:CODE:";
    /**
     * redis key
     */
    public static final String AUTH_CODE_IMAGE = "AUTH:CODE:IMAGE:";
    /**
     * redis key
     */
    public static final String AUTH_CODE_MOBILE = "AUTH:CODE:MOBILE:";
    /**
     * redis key
     */
    public static final String AUTH_CODE_MOBILE_NUM = "AUTH:CODE:MOBILE:NUM:";

}
