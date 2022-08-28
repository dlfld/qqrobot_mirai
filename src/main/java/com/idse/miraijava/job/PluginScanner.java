package com.idse.miraijava.job;

import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.OnMessage;
import com.idse.miraijava.annotation.Plugin;
import com.idse.miraijava.pojo.MiraiConfig;
import com.idse.miraijava.pojo.PluginPair;
import com.idse.miraijava.reflects.Scanner;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Set;

@Slf4j
public class PluginScanner {
    /**
     * 扫描所有的组件注解
     */
    @SneakyThrows
    public void scanPlugins() {

        MiraiConfig config = BotSave.getMiraiConfig();
        //找到带有Plugin注解的类
        Set<Class<?>> annotationClasses = new Scanner().getAnnotationClasses(config.getPluginsDir(), Plugin.class);
        // 遍历每一个类
        for (Class<?> aClass : annotationClasses) {
//            获取到每一个类中的方法
            Method[] declaredMethods = aClass.getDeclaredMethods();

            for (Method method : declaredMethods) {
                /**
                 * 获取命令式方法 start
                 */
                //获取加了command注解的方法
                Command[] annotationsByType = method.getAnnotationsByType(Command.class);
//                数组长度大于0 表示是找到了这样的方法的
                if (annotationsByType.length > 0) {
                    Command command = annotationsByType[0];
//                command注解里面填写的值
                    String commandValue = command.command();
//                添加当前命令和方法进入全局的map中
                    PluginSave.addCommandMethod(commandValue, new PluginPair().setMethod(method).setClazz(aClass));
                }


                /**
                 * 获取命令式方法 end
                 */
                OnMessage[] onMessages = method.getAnnotationsByType(OnMessage.class);
                if (onMessages.length > 0) {
                    PluginSave.addOnMessageMethod(new PluginPair().setMethod(method).setClazz(aClass));
                }
            }

        }

    }

}
