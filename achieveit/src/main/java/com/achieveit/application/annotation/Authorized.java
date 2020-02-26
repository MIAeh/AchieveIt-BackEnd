package com.achieveit.application.annotation;

import java.lang.annotation.*;

/**
 * @Title: Authorized
 * @Description: 自定义注解@Authorized,被注解的service包中的方法都会被AuthorizationAspect验证Token
 * @Author: Felix
 * @Date: 6/2/2018 17:00
 * @Version: 1.0
 **/

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Authorized {
}
