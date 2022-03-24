package com.example.miraijava.plugins;

import com.example.miraijava.annotation.Plugin;
import com.example.miraijava.interfaces.CommandHandle;

//import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/18 1:26 下午
 * @Version 1.0
 */
@Plugin(command = "测试")
public class TestPlugin implements CommandHandle {
    @Override
    public void handleCommand(MessageEvent event) {
        System.out.println("进来了");

    }
}
