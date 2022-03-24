package com.example.miraijava.plugins;

import com.example.miraijava.annotation.Command;
import com.example.miraijava.annotation.Plugin;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/24 13:46
 * @Version 1.0
 */
@Plugin
public class Report {

    @Command(command = "测试")
    public void handleCommand(MessageEvent event) {
        System.out.println("进来了");
    }

}
