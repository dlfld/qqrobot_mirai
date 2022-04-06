package com.idse.miraijava.annotation;


import java.lang.annotation.*;



@Target(ElementType.TYPE)  //作用在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Plugin {
    String command() default " ";
}