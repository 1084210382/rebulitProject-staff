package com.example.demo.commonRequest.aop;

/**
 * @author whn
 */
public @interface Log {
    String operation() default "";
    int type();
}
