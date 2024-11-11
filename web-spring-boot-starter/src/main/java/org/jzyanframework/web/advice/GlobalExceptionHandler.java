package org.jzyanframework.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.jzyanframework.core.enums.ResCodeEnum;
import org.jzyanframework.core.exception.BusinessException;
import org.jzyanframework.core.exception.SystemException;
import org.jzyanframework.core.response.CommonRes;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.Set;

/**
 * SpringMVC统一异常处理
 *
 * @author jzyan
 * @date 2020-09-07
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获Validation的异常信息
     *
     * @param e
     * @return CommonRes
     */
    @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
    public CommonRes validException(Exception e) {
        StringBuffer errorMessage = new StringBuffer();
        BindingResult bindingResult;
        if (e instanceof BindException) {
            bindingResult = ((BindException) e).getBindingResult();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField())
                        .append(":")
                        .append(fieldError.getDefaultMessage())
                        .append(", ");
            }
        } else if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                errorMessage.append(fieldError.getField())
                        .append(":")
                        .append(fieldError.getDefaultMessage())
                        .append(", ");
            }
        } else {
            errorMessage.append("Validation 参数处理异常");
            return CommonRes.failed(errorMessage.toString());
        }
        log.warn(ResCodeEnum.VALIDATE_FAILED.getMessage() + ": {}", errorMessage.toString());
        return CommonRes.failed(ResCodeEnum.VALIDATE_FAILED, errorMessage.toString());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonRes missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.warn(ResCodeEnum.VALIDATE_FAILED.getMessage() + ": {}", e.getMessage());
        return CommonRes.failed(e.getMessage());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonRes constraintViolationExceptionHandler(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuffer errorMessage = new StringBuffer();
        Iterator iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            ConstraintViolation obj = (ConstraintViolation) iterator.next();
            errorMessage.append(obj.getMessage()).append(", ");
        }
        log.warn(ResCodeEnum.VALIDATE_FAILED.getMessage() + ": {}", errorMessage.toString());
        return CommonRes.failed(ResCodeEnum.VALIDATE_FAILED, errorMessage.toString());
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public CommonRes noHandlerFoundExceptionHandler(NoHandlerFoundException e) {
        log.warn("请求路径不存在: {}", e.getMessage());
        return CommonRes.failed(ResCodeEnum.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public CommonRes methodNotAllowed(HttpRequestMethodNotSupportedException e) {
        log.warn("请求方法错误: {}", e.getMessage());
        return CommonRes.failed(ResCodeEnum.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public CommonRes methodNotAllowed(HttpMediaTypeNotSupportedException e) {
        log.warn("Content-Type类型错误: {}", e.getMessage());
        return CommonRes.failed(ResCodeEnum.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(value = Throwable.class)
    public CommonRes unknownException(Throwable e) {
        log.error("未知异常: {}", e);
        return CommonRes.failed();
    }

    @ExceptionHandler(value = BusinessException.class)
    public CommonRes businessException(BusinessException e) {
        log.warn("业务异常: {}", e.getMessage());
        return CommonRes.failed(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = SystemException.class)
    public CommonRes systemException(SystemException e) {
        log.error("系统异常: {}", e);
        return CommonRes.failed(e.getCode(), e.getMessage());
    }

}
