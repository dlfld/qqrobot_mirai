package com.idse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan({"com.idse.miraijava"})
//@SpringBootApplication
public class MiraiJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiraiJavaApplication.class, args);
    }
}

