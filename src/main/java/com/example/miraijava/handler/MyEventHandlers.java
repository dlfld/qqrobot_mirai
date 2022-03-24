package com.example.miraijava.handler;

import com.example.miraijava.annotation.Command;
import com.example.miraijava.annotation.Plugin;
import com.example.miraijava.reflects.Scanner;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MyEventHandlers extends SimpleListenerHost {

    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        Set<Class<?>> annotationClasses = new Scanner().getAnnotationClasses("com.example.miraijava.plugins", Plugin.class);
//        找到带有Plugin 注解的类
        for (Class<?> aClass : annotationClasses) {
            Method[] declaredMethods = aClass.getDeclaredMethods();
            //  扫描类中的每一个方法
            for (Method method : declaredMethods) {
                Command[] annotationsByType = method.getAnnotationsByType(Command.class);
                System.out.println(annotationsByType.length);
                if (annotationsByType.length > 0) {
                    Command command = annotationsByType[0];
                    String commandValue = command.command();
//                    用户发的信息
                    String userCommand = event.getMessage().get(1) + "";
//                    如果命令匹配的话
                    if (Objects.equals(userCommand.strip(), commandValue.strip())) {
//                        调用方法
                        try {
                            method.invoke(aClass.getDeclaredConstructor().newInstance(), event);
                        } catch (IllegalArgumentException illegalArgumentException) {
                            log.error("请在命令方法上加上参数 MessageEvent event");
                        }

                    }
                }
            }
        }
    }
}
