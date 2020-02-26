package com.achieveit.application.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title: HttpRequestAspect
 * @Description: 收到Http请求时，通过该切面记录日志
 * @Author: Felix
 * @Date: 6/2/2018 22:52
 * @Version: 1.0
 **/

@Aspect
@Component
public class HttpRequestAspect {

    private final static Logger logger = LoggerFactory.getLogger("com.tripin.application.aspect.HttpRequestAspect");

    @Pointcut("within(com.tripin.application.controller..*))")
    public void pointcut() {
    }

    @Before("pointcut() && @annotation(postMapping)")
    public void receiveHttpPostRequest(JoinPoint joinPoint, PostMapping postMapping) {
        this.receiveHttpRequest(joinPoint);
    }

    @Before("pointcut() && @annotation(getMapping)")
    public void receiveHttpPostRequest(JoinPoint joinPoint, GetMapping getMapping) {
        this.receiveHttpRequest(joinPoint);
    }

    private void receiveHttpRequest(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String signature = joinPoint.getSignature().toLongString(); // 获取目标方法签名
        logger.info("==== Receive Http Request ====");
        logger.info("Request URL: {}", request.getRequestURL());
        logger.info("Request Method: {}", request.getMethod());
        logger.info("Request IP: {}", request.getRemoteAddr());
        logger.info("Method Signature: " + signature);
        logger.info("==============================");
        logger.info("");
    }
}
