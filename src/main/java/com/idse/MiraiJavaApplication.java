package com.idse;

import com.idse.miraijava.job.StartBot;
import com.idse.miraijava.pojo.MiraiConfig;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.idse.miraijava"})
//@SpringBootApplication
public class MiraiJavaApplication {

    public static void main(String[] args) {
        new StartBot().run(new MiraiConfig()
                .setQq("3152364256")
                .setPassword("2441086385dlf")
                .setPluginsDir("com.idse.miraijava.plugins")
                .setProtocol(BotConfiguration.MiraiProtocol.ANDROID_PAD)
                //.setWorkDir("/home/dlf/runcode/work_dir"));

                //.setWorkDir("/home/ljc/workspace/java/bot/src/main/resources/work_dir"));
                .setWorkDir("/Users/dailinfeng/Desktop/小项目/mirai-java-framework/src/main/resources/work_dir"));
        SpringApplication.run(MiraiJavaApplication.class, args);

    }
}

