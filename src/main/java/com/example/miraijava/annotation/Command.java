package com.example.miraijava.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author dailinfeng
 * @Description 具体的命令注解
 * @Date 2022/3/24 14:17
 * @Version 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String command() default "";
}
