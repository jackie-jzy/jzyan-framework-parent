package org.jzyan.framework.core.response;

/**
 * @FileName : ResCode
 * @Version : 1.0
 * @Description : 封装API的错误码
 * @Author : jzyan
 * @CreateDate : 2020/09/11 09:50
 */
public interface ResCode {

    long getCode();

    String getMessage();
}
