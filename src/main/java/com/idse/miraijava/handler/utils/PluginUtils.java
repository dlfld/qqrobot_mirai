package com.idse.miraijava.handler.utils;

import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.OnMessage;
import com.idse.miraijava.job.BotSave;
import com.idse.miraijava.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.event.events.MessageEvent;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;


import java.beans.Introspector;
import java.lang.reflect.Method;
import java.util.Objects;


@Slf4j
public class PluginUtils {

    public static void invokeMethod(){

    }
    /**
     * 处理Command注解对应的功能
     *
     * @param method
     */
    @Deprecated
    public static void handeCommand(Method method, Class<?> aClass, MessageEvent event) {

        //获取加了command注解的方法
        Command[] annotationsByType = method.getAnnotationsByType(Command.class);
        if (annotationsByType.length > 0) {
            Command command = annotationsByType[0];
            String commandValue = command.command();
//                    用户发的信息
            String userCommand = event.getMessage().get(1) + "";
//                    如果命令匹配的话 消息为纯命令或者是开头是命令加上分隔符开头
            // 命令分隔符
            String commandSplit = BotSave.getMiraiConfig().getCommandSplit();
            if (Objects.equals(userCommand.strip(), commandValue.strip()) ||
                    userCommand.strip().startsWith(commandValue.strip() + commandSplit)) {
//                        调用方法
                try {
                    //spring方式调用
                    //根据全类名获取当前类的短类名
                    String shortClassName = ClassUtils.getShortName(aClass.getName());
                    /**
                     *  这个方法是Sping 将类放进容器时生成名字的工具类
                     *   其命名规则是：
                     *      如果类名第一个是大写第二个是小写则将第一个转化为小写
                     *      如果类名前两个都是大写那么则直接是类名
                     */
                    String className = Introspector.decapitalize(shortClassName);
                    Object pluginClass = SpringContextUtil.getBean(className);
                    Method method_spring = ReflectionUtils.findMethod(pluginClass.getClass(), method.getName(), MessageEvent.class, String.class);
                    ReflectionUtils.invokeMethod(method_spring, pluginClass, event, userCommand);
                } catch (IllegalArgumentException illegalArgumentException) {
                    log.error("请在命令方法上加上参数 MessageEvent event,String message");
                    illegalArgumentException.printStackTrace();
                }

            }
        }
    }

    /**
     * 处理消息接收事件
     *
     * @param method
     * @param aClass
     * @param event
     */
    @Deprecated
    public static void handleOnMessage(Method method, Class<?> aClass, MessageEvent event) {
        //获取注解为OnMessage的注解
        OnMessage[] annotationsByType = method.getAnnotationsByType(OnMessage.class);
        if (annotationsByType.length > 0) {
            //获取到用户发送的信息
            String message = event.getMessage().get(1) + "";
            try {
                //spring方式调用
                //根据全类名获取当前类的短类名
                String shortClassName = ClassUtils.getShortName(aClass.getName());
                String className = Introspector.decapitalize(shortClassName);
                Object pluginClass = SpringContextUtil.getBean(className);
                Method method_spring = ReflectionUtils.findMethod(pluginClass.getClass(), method.getName(), MessageEvent.class, String.class);
                ReflectionUtils.invokeMethod(method_spring, pluginClass, event, message);

            } catch (IllegalArgumentException illegalArgumentException) {
                log.error("请在命令方法上加上参数 MessageEvent event,String message");
                illegalArgumentException.printStackTrace();
            }
        }

    }
}
