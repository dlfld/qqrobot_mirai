package com.idse;

import com.idse.miraijava.job.StartBot;
import com.idse.miraijava.pojo.MiraiConfig;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.idse.miraijava"})
public class MiraiJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiraiJavaApplication.class, args);

    }
}

