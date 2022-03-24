package com.idse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration;

//@EnableScheduling
@SpringBootApplication(exclude = BatchAutoConfiguration.class)
public class MiraiJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiraiJavaApplication.class, args);
    }
}
