package com.idse.miraijava.handler;

import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.Plugin;
import com.idse.miraijava.job.BotSave;
import com.idse.miraijava.pojo.MiraiConfig;
import com.idse.miraijava.reflects.Scanner;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.MessageEvent;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;


@Slf4j
public class MyEventHandlers extends SimpleListenerHost {


    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        MiraiConfig config = BotSave.getMiraiConfig();
        Set<Class<?>> annotationClasses = new Scanner().getAnnotationClasses(config.getPluginsDir(), Plugin.class);
//        找到带有Plugin 注解的类
        for (Class<?> aClass : annotationClasses) {
            Method[] declaredMethods = aClass.getDeclaredMethods();
            //  扫描类中的每一个方法
            for (Method method : declaredMethods) {
                Command[] annotationsByType = method.getAnnotationsByType(Command.class);
                if (annotationsByType.length > 0) {
                    Command command = annotationsByType[0];
                    String commandValue = command.command();
//                    用户发的信息
                    String userCommand = (event.getMessage().get(1) + "");
//                    如果命令匹配的话
                    if (Objects.equals(userCommand.strip(), commandValue.strip()) || userCommand.strip().startsWith(commandValue.strip())) {
//                        调用方法
                        try {
                            method.invoke(aClass.getDeclaredConstructor().newInstance(), event, event.getMessage().get(1) + "");
                        } catch (IllegalArgumentException illegalArgumentException) {
                            log.error("请在命令方法上加上参数 MessageEvent event,String message");
                        }

                    }
                }
            }
        }
    }
}
