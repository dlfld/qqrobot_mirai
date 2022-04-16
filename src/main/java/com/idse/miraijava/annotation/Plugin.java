package com.idse.miraijava.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;



@Target(ElementType.TYPE)  //作用在类上
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Plugin {
    String command() default " ";
}