package com.idse.miraijava.handler;

import com.idse.miraijava.handler.utils.PluginUtils;
import com.idse.miraijava.job.PluginSave;
import com.idse.miraijava.pojo.PluginPair;
import com.idse.miraijava.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.EventHandler;
import net.mamoe.mirai.event.SimpleListenerHost;
import net.mamoe.mirai.event.events.MessageEvent;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.Introspector;
import java.lang.reflect.Method;

/**
 * todo
 *      将命令处理改为注册式的方法而不是每一个命令来就扫描一次
 */
@Slf4j
public class MyEventHandlers extends SimpleListenerHost {
    @EventHandler
    public void onMessage(@NotNull MessageEvent event) throws Exception { // 可以抛出任何异常, 将在 handleException 处理
        //获取到用户发送的信息
        String message = event.getMessage().get(1) + "";
        for (PluginPair pluginPair : PluginSave.getOnMessageMethods()) {
            PluginUtils.funcCall(pluginPair, event);
        }
        PluginPair methodByCommand = PluginSave.getMethodByCommand(message);
        PluginUtils.funcCall(methodByCommand, event);
//        MiraiConfig config = BotSave.getMiraiConfig();
//        Set<Class<?>> annotationClasses = new Scanner().getAnnotationClasses(config.getPluginsDir(), Plugin.class);
////        找到带有Plugin 注解的类
//        for (Class<?> aClass : annotationClasses) {
//            Method[] declaredMethods = aClass.getDeclaredMethods();
//            //  扫描类中的每一个方法
//            for (Method method : declaredMethods) {
//
//                CompletableFuture<Void> handeCommandFuture = CompletableFuture.runAsync(() -> {
////                    处理命令式方法
//                    PluginUtils.handeCommand(method, aClass, event);
//                });
//                handeCommandFuture.get();
//                CompletableFuture<Void> handleOnMessageFuture = CompletableFuture.runAsync(() -> {
//                    //处理全部消息的情况
//                    PluginUtils.handleOnMessage(method, aClass, event);
//                });
//
//                handleOnMessageFuture.get();
//            }
//        }
    }
}
