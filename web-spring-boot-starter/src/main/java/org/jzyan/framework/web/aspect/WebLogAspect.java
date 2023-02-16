package org.jzyan.framework.web.aspect;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.jzyan.framework.core.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

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
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, String> map = new TreeMap<>();
        map.put("userAgent", request.getHeader("user-agent"));
        map.put("param", gson.toJson(joinPoint.getArgs()));
        Object result = joinPoint.proceed();
        String clientIp = IpUtils.getIpAddr(request);
        String requestUrl = request.getMethod() + ":" + request.getRequestURL().toString();
        String requestParam = gson.toJson(map);
        String responseParam = gson.toJson(result);
        long gapTime = System.currentTimeMillis() - startTime;
        log.info("[{}]-[{}ms]-[{}] request:{} response:{}", clientIp, gapTime, requestUrl, requestParam, responseParam);
        return result;
    }

}
