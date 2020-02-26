package com.achieveit.application.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    private static final Logger logger = LoggerFactory.getLogger("com.tripin.application.aspect.AuthorizationAspect");

//    private final UserInfoService userInfoService;
//
//    @Autowired
//    public AuthorizationAspect(UserInfoService userInfoService) {
//        this.userInfoService = userInfoService;
//    }

//    @Pointcut("within(com.tripin.application.service..*))")
//    public void pointcut() {
//    }
//
//    @Before("pointcut() && @annotation(com.tripin.application.annotation.Authorized)")
//    public void authorize(JoinPoint joinPoint) {
//        Token token = null;
//        Object[] objects = joinPoint.getArgs();
//        for (Object object : objects) {
//            if (object instanceof Token) {
//                token = (Token) object;
//                break;
//            }
//        }
//        HashMap tokenHashMap = userInfoService.getTokenHashMap();
//
//        // 验证Token
//        if(null == token || !tokenHashMap.containsKey(token.getToken()) || !token.equals(tokenHashMap.get(token.getToken()))) {
//            logger.info("Failed to Authorize.");
//            logger.info("");
//            throw new TripinException(ErrorCode.UNAUTHORIZED);
//        }
//        else {
//            logger.info("Successfully Authorize.");
//            logger.info("");
//        }
//    }
}
