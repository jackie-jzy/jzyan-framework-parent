package org.jzyanframework.web.advice;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.jzyanframework.core.enums.ResCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.RequestDispatcher;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 自定义错误json格式
 * </p>
 *
 * @author jzyan
 * @since 2023-02-15
 */
@Slf4j
@Component
public class CustomizeErrorAttributes extends DefaultErrorAttributes {
    @Autowired
    private Gson gson;

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        Integer status = getAttribute(webRequest, RequestDispatcher.ERROR_STATUS_CODE);
        if (status == null) {
            errorAttributes.put("code", ResCodeEnum.FAILED.getCode());
            errorAttributes.put("message", ResCodeEnum.FAILED.getMessage());
            return errorAttributes;
        }
        errorAttributes.put("code", status);
        try {
            errorAttributes.put("message", HttpStatus.valueOf(status).getReasonPhrase());
        } catch (Exception ex) {
            // Unable to obtain a reason
            errorAttributes.put("message", "Http Status " + status);
        }
        errorAttributes.put("dateTime", new Date());
        String path = getAttribute(webRequest, RequestDispatcher.ERROR_REQUEST_URI);
        log.error("请求异常 Path: {}, msg: {}", path, gson.toJson(errorAttributes));
        return errorAttributes;
    }

    @SuppressWarnings("unchecked")
    private <T> T getAttribute(RequestAttributes requestAttributes, String name) {
        return (T) requestAttributes.getAttribute(name, RequestAttributes.SCOPE_REQUEST);
    }

}
