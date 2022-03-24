package com.example;

import com.example.miraijava.handler.MyEventHandlers;
import com.example.miraijava.job.BotSave;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;

//@EnableScheduling
@SpringBootApplication
public class MiraiJavaApplication {
    public static void main(String[] args) {
        SpringApplication.run(MiraiJavaApplication.class, args);
    }
}
