package org.jzyanframework.core.enums;

import org.jzyanframework.core.response.ResCode;

/**
 * @FileName : ResCode
 * @Version : 1.0
 * @Description : 枚举了一些常用API操作码
 * @Author : jzyan
 * @CreateDate : 2019/04/17 20:47
 */
public enum ResCodeEnum implements ResCode {
    SUCCESS("0000", "成功"),
    FAILED("00461099", "系统忙"),
    CODE_00461000("00461000", "签名验签失败"),
    VALIDATE_FAILED("00461001", "参数检验失败"),

    NOT_FOUND("404", "请求路径不存在"),
    METHOD_NOT_ALLOWED("405", "请求方法错误"),
    UNSUPPORTED_MEDIA_TYPE("415", "Content-Type类型错误"),
    ;

    private String code;
    private String message;

    ResCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
