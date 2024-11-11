package org.jzyanframework.web.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jzyanframework.core.exception.BusinessException;
import org.jzyanframework.core.response.CommonRes;
import org.jzyanframework.core.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Version : 1.0
 * @Description : 统一日志处理
 * @Author : jzyan
 * @CreateDate : 2019/12/05 10:22
 */
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Autowired
    private Gson gson;

    @Around("@within(org.springframework.web.bind.annotation.RestController)")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object[] args = joinPoint.getArgs();
        Object[] objects = new Object[args.length];
        if (!ObjectUtils.isEmpty(args)) {
            for (int i = 0; i < args.length; i++) {
                Object object = args[i];
                if (!(object instanceof MultipartFile)
                        && !(object instanceof HttpServletRequest)
                        && !(object instanceof HttpServletResponse)) {
                    objects[i] = object;
                }
            }
        }
        String clientIp = IpUtils.getIpAddr(request);
        String userAgent = request.getHeader("user-agent");
        String requestUrl = request.getMethod() + ":" + request.getRequestURL().toString();
        String responseParam = "";
        try {
            log.info("[{}]-[{}] Cli:{} 请求入参:{}", clientIp, requestUrl, userAgent, gson.toJson(objects));
            Object result = joinPoint.proceed();
            responseParam = gson.toJson(result);
            return result;
        } catch (BusinessException e) {
            responseParam = gson.toJson(CommonRes.failed(e.getCode(), e.getMessage()));
            throw e;
        } catch (Exception e) {
            responseParam = gson.toJson(CommonRes.failed(e.getMessage()));
            throw e;
        } finally {
            long gapTime = System.currentTimeMillis() - startTime;
            log.info("[{}ms]-[{}] 请求出参:{}", gapTime, requestUrl, responseParam);
        }
    }
}
