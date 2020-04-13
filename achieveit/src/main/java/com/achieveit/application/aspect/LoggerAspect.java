package com.achieveit.application.aspect;

import com.achieveit.application.annotation.Logged;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @Title: LoggerAspect
 * @Description: 以切面的方式来实现的日志，记录controller和service包下所有被@Logged注解的的方法
 * @Author: Felix
 * @Date: 6/2/2018 13:17
 * @Version: 1.0
 **/

@Aspect
@Component
public class LoggerAspect {

    private static final Logger logger = LoggerFactory.getLogger("com.achieveit.application.aspect.LoggerAspect");

    @Pointcut("within(com.achieveit.application.service..*))")
    public void servicePointcut() {
    }

    @Pointcut("within(com.achieveit.application.controller..*))")
    public void controllerPointcut() {
    }

    @Before("(servicePointcut() || controllerPointcut()) && @annotation(logged)")
    public void logBefore(JoinPoint joinPoint, Logged logged) {
        String signature = joinPoint.getSignature().toLongString(); // 获取目标方法签名
        String  parameterValues = Arrays.toString(joinPoint.getArgs());// 获取目标方法体参数
        String parameterNames = Arrays.toString(logged.value());
        logger.info("==== Before Execute ====");
        logger.info("Method Signature: " + signature);
        logger.info("Parameter names and value(s): " + parameterNames + " " + parameterValues);
        logger.info("========================");
        logger.info("");
    }

    @After("(servicePointcut() || controllerPointcut()) && @annotation(logged)")
    public void logAfter(JoinPoint joinPoint, Logged logged) {
        String signature = joinPoint.getSignature().toLongString(); // 获取目标方法签名
        String parameterValues = Arrays.toString(joinPoint.getArgs());// 获取目标方法体参数
        String parameterNames = Arrays.toString(logged.value());
        logger.info("==== After  Execute ====");
        logger.info("Method Signature: " + signature);
    }

    @AfterReturning(pointcut = "(servicePointcut() || controllerPointcut()) && @annotation(logged)", returning = "returnValue")
    public void logAfterReturning(JoinPoint joinPoint, Logged logged, Object returnValue) {
        if(null != returnValue) {
            logger.info("Returns: " + returnValue.getClass().toString() + " " + returnValue.toString());
        }
        logger.info("=== Execute  Success ===");
        logger.info("");
    }

    @AfterThrowing(pointcut = "(servicePointcut() || controllerPointcut()) && @annotation(logged)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Logged logged, RuntimeException exception) {
        logger.error("Exception Message: " + exception.getClass().getName() + ": " + exception.getMessage());
        logger.error("Exception Stack Trace: " + Arrays.toString(exception.getStackTrace()));
        logger.info("==== Execute Failed ====");
        logger.info("");
    }
}
