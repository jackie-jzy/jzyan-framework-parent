package org.jzyanframework.core.aspect;

import org.jzyanframework.core.annotation.SignVerify;
import org.jzyanframework.core.enums.ResCodeEnum;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.jzyanframework.core.response.CommonRes;
import org.jzyanframework.core.utils.SignatureUtil;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 签名验证
 * </p>
 *
 * @author jzyan
 * @since 2022-05-13
 */
@Slf4j
@Aspect
@Component
public class SignVerifyAspect implements EnvironmentAware {

    private Environment environment;

    @Around("@annotation(org.jzyanframework.core.annotation.SignVerify)")
    public Object handleSignVerify(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String[] names = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        SignVerify annotation = method.getAnnotation(SignVerify.class);

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String reqMethod = request.getMethod();
        String sign = request.getHeader("sign");
        if (StringUtils.isBlank(sign)) {
            sign = request.getParameter("sign");
        }
        Map<String, Object> map = new HashMap<>();
        if (reqMethod.toLowerCase().equals("get")) {
            for (int i = 0; i < names.length; i++) {
                map.put(names[i], joinPoint.getArgs()[i]);
            }
        } else {
            map = JSONObject.fromObject(joinPoint.getArgs()[0]);
        }
        if (StringUtils.isNotBlank(sign)) {
            map.put("sign", sign);
        }
        if (SignatureUtil.verifySignature(this.environment.resolvePlaceholders(annotation.value()), map)) {
            return joinPoint.proceed();
        }
        return CommonRes.failed(ResCodeEnum.CODE_00461000);

    }

    /**
     * Set the {@code Environment} that this component runs in.
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

}
