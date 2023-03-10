package org.jzyan.framework.core.response;

import com.alibaba.fastjson.annotation.JSONField;
import org.jzyan.framework.core.enums.ResCodeEnum;

import java.util.Date;

/**
 * 通用返回对象。所有以JSON格式向前端返回的报文，除特殊格式要求外，均返回该对象
 */
public class CommonRes<T> {
    /**
     * 返回码
     */
    private long code;
    /**
     * 返回提示信息
     */
    private String message;
    /**
     * 返回数据 默认 null
     */
    private T data;
    /**
     * 返回时间 yyyy-MM-dd HH:mm:ss
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date dateTime;

    protected CommonRes() {
    }

    protected CommonRes(long code, String message, T data, Date dateTime) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.dateTime = dateTime;
    }

    /**
     * 成功返回结果
     */
    public static <T> CommonRes<T> success() {
        return success(null);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonRes<T> success(T data) {
        return success(data, ResCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> CommonRes<T> success(T data, String message) {
        return new CommonRes<T>(ResCodeEnum.SUCCESS.getCode(), message, data, new Date());
    }

    /**
     * 失败返回结果
     *
     * @param codeEnum 提示信息
     */
    public static <T> CommonRes<T> failed(ResCode codeEnum) {
        return failed(null, codeEnum);
    }

    /**
     * 失败返回结果
     *
     * @param data     获取的数据
     * @param codeEnum 提示信息
     */
    public static <T> CommonRes<T> failed(T data, ResCode codeEnum) {
        return new CommonRes<T>(codeEnum.getCode(), codeEnum.getMessage(), data, new Date());
    }

    /**
     * 失败返回结果
     *
     * @param code 获取的数据
     * @param msg  提示信息
     */
    public static <T> CommonRes<T> failed(long code, String msg) {
        return new CommonRes<T>(code, msg, null, new Date());
    }

    /**
     * 失败返回结果
     *
     * @param codeEnum 错误码
     * @param message  错误信息
     */
    public static <T> CommonRes<T> failed(ResCode codeEnum, String message) {
        return new CommonRes<T>(codeEnum.getCode(), message, null, new Date());
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonRes<T> failed(String message) {
        return new CommonRes<T>(ResCodeEnum.FAILED.getCode(), message, null, new Date());
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonRes<T> failed() {
        return failed(ResCodeEnum.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonRes<T> validateFailed() {
        return failed(ResCodeEnum.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> CommonRes<T> validateFailed(String message) {
        return new CommonRes<T>(ResCodeEnum.VALIDATE_FAILED.getCode(), message, null, new Date());
    }

    /**
     * 未认证返回结果
     */
    public static <T> CommonRes<T> unauthorized(T data) {
        return new CommonRes<T>(ResCodeEnum.UNAUTHORIZED.getCode(), ResCodeEnum.UNAUTHORIZED.getMessage(), data, new Date());
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonRes<T> forbidden(T data) {
        return new CommonRes<T>(ResCodeEnum.FORBIDDEN.getCode(), ResCodeEnum.FORBIDDEN.getMessage(), data, new Date());
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}