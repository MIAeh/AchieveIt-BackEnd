package com.achieveit.application.aspect;

import com.achieveit.application.domain.Token;
import com.achieveit.application.enums.ErrorCode;
import com.achieveit.application.exception.AchieveitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Title: AuthorizationAspect
 * @Description:
 *  登陆验证功能，拦截需要权限的请求
 *  凡是带有 @Authorized 都要进行功能权限检查
 *  若无权限，则抛出AccessDeniedException 异常，该异常将请求转发至一个控制器，然后将异常结果返回
 *
 * @Author: Felix
 * @Date: 5/31/2018 17:28
 * @Version: 1.0
 **/

@Aspect
@Component
public class AuthorizationAspect {

    private static final Logger logger = LoggerFactory.getLogger("com.achieveit.application.aspect.AuthorizationAspect");

    @Autowired
    public AuthorizationAspect() {
    }

    @Pointcut("within(com.achieveit.application.service..*))")
    public void pointcut() {
    }

    @Before("pointcut() && @annotation(com.achieveit.application.annotation.Authorized)")
    public void authorize(JoinPoint joinPoint) {
        Token token = null;
        Object[] objects = joinPoint.getArgs();
        for (Object object : objects) {
            if (object instanceof Token) {
                token = (Token) object;
                break;
            }
        }

//        HashMap tokenHashMap = userInfoService.getTokenHashMap();
//
//        // 验证Token
//        if(null == token || !tokenHashMap.containsKey(token.getToken()) || !token.equals(tokenHashMap.get(token.getToken()))) {
//            logger.info("Failed to Authorize.");
//            logger.info("");
//            try {
//                throw new AchieveitException(ErrorCode.UNAUTHORIZED);
//            } catch (AchieveitException e) {
//                e.printStackTrace();
//            }
//        }
//        else {
//            logger.info("Successfully Authorize.");
//            logger.info("");
//        }
    }
}
