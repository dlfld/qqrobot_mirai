package com.idse;

import com.idse.miraijava.job.StartBot;
import com.idse.miraijava.pojo.MiraiConfig;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.idse.miraijava"})
//@SpringBootApplication
public class MiraiJavaApplication {

    public static void main(String[] args) {


//        new StartBot().run(new MiraiConfig()
//                .setQq("460796757")
//                .setPassword("2441086385dlf..")
//                .setPluginsDir("com.idse.miraijava.plugins") //插件包的路径，插件包需要自己创建 类似于Spring Mvc中的controller包
//                .setWorkDir("src/main/resources/work_dir")); //work_dir的路径
        SpringApplication.run(MiraiJavaApplication.class, args);

    }
}

