package com.idse.miraijava.job;

import com.idse.miraijava.pojo.PluginPair;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class PluginSave {
    /**
     * 命令对应方法存储
     */
    private static HashMap<String, PluginPair> commandMethod = new HashMap<>();
    /**
     * 收到消息就反应的list
     */
    private static List<PluginPair> onMessageMethods = new LinkedList<>();

    /**
     * 获取收到消息时需要执行的方法
     *
     * @return 收到消息时需要执行的方法列表
     */
    public static List<PluginPair> getOnMessageMethods() {
        return onMessageMethods;
    }

    /**
     * 添加收到消息就执行的方法
     *
     * @param pluginPair 方法
     * @return 是否添加成功
     */
    public static boolean addOnMessageMethod(PluginPair pluginPair) {
        return onMessageMethods.add(pluginPair);
    }

    /**
     * 根据命令获取命令对应的方法
     *
     * @param command 命令
     * @return 命令对应的可调用的方法
     */
    public static PluginPair getMethodByCommand(@NotNull String command) {
        return commandMethod.getOrDefault(command, null);
    }

    /**
     * 根据命令添加命令对应的方法
     *
     * @param command    命令
     * @param pluginPair 命令对应的方法
     * @param override   如果命令重复是否覆盖
     * @return 添加是否成功
     */
    public static boolean addCommandMethod(@NotNull String command, @NotNull PluginPair pluginPair, boolean override) {
        if (override) {
            commandMethod.put(command, pluginPair);
            return true;
        }
        if (commandMethod.containsKey(command)) {
            return false;
        }
        commandMethod.put(command, pluginPair);
        return true;
    }

    /**
     * 根据命令添加命令对应的方法
     *
     * @param command    命令
     * @param pluginPair 命令对应的方法dui
     * @return 添加是否成功
     */
    public static boolean addCommandMethod(@NotNull String command, @NotNull PluginPair pluginPair) {
        commandMethod.put(command, pluginPair);
        return true;
    }
}
