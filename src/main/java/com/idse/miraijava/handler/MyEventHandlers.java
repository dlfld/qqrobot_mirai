package com.idse.miraijava.handler;

import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.Plugin;
import com.idse.miraijava.handler.utils.PluginUtils;
import com.idse.miraijava.job.BotSave;
import com.idse.miraijava.pojo.MiraiConfig;
import com.idse.miraijava.reflects.Scanner;
import com.idse.miraijava.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.MessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import javax.annotation.Resource;
import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.Arrays;
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
                //处理命令式方法
                PluginUtils.handeCommand(method, aClass, event);
                
            }
        }
    }
}
