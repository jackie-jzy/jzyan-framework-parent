package org.jzyan.framework.core.enums;

import org.jzyan.framework.core.response.ResCode;

/**
 * @FileName : ResCode
 * @Version : 1.0
 * @Description : 枚举了一些常用API操作码
 * @Author : jzyan
 * @CreateDate : 2019/04/17 20:47
 */
public enum ResCodeEnum implements ResCode {
    SUCCESS(200, "操作成功"),
    REDIRECT(302, "URL重定向"),
    CODE_400(400, "签名错误"),
    UNAUTHORIZED(401, "未登录或登录超时"),
    FORBIDDEN(403, "无权访问"),
    NOT_FOUND(404, "请求路径不存在"),
    METHOD_NOT_ALLOWED(405, "请求方法错误"),
    VALIDATE_FAILED(406, "参数检验失败"),
    UNSUPPORTED_MEDIA_TYPE(415, "Content-Type类型错误"),
    FAILED(500, "服务器内部错误"),

    ;

    private long code;
    private String message;

    ResCodeEnum(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
