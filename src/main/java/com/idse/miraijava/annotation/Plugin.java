package com.idse.miraijava.annotation;


import java.lang.annotation.*;


/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/18 1:21 下午
 * @Version 1.0
 */
@Target(ElementType.TYPE)  //作用在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Plugin {
    String command() default " ";
}