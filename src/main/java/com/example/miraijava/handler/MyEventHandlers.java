package com.example.miraijava.handler;

import com.example.miraijava.annotation.Plugin;
import com.example.miraijava.reflects.Scanner;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.data.SingleMessage;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2021/9/18 12:59 下午
 * @Version 1.0
 */

public class MyEventHandlers extends SimpleListenerHost {

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        Set<Class<?>> annotationClasses = new Scanner().getAnnotationClasses("com.example.miraijava.plugins", Plugin.class);

        for (Class<?> aClass : annotationClasses) {
            //          获取到指定的注解
            Plugin[] plugins = aClass.getAnnotationsByType(Plugin.class);
//            System.out.println(plugins);
            for (Plugin plugin : plugins) {
//               如果命令是plugin中申明的命令则调用相应的执行方法
                String command = event.getMessage().get(1) + "";
                if (Objects.equals(command, plugin.command())) {
                    //通过方法名获取到方法
                    Method handleCommand = aClass.getMethod("handleCommand", MessageEvent.class);
//                    调用方法
                    handleCommand.invoke(aClass.getDeclaredConstructor().newInstance(), event);
                }
            }
        }
    }
}
