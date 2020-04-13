package com.achieveit.application.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Logged {

    /**
     * 参数名称
     */
    String[] value() default {"NoParameterNameIsGiven"};
}
