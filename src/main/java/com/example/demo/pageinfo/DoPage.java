package com.example.demo.pageinfo;

import java.lang.annotation.*;

/**
 * @author whn
 * 分页注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface DoPage {
}
