package com.achieveit.application.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface PostControl {

    /**
     * projectID 参数位置
     */
    int value() default 0;
}
