package com.idse.miraijava.job;

import com.idse.miraijava.pojo.MiraiConfig;
import net.mamoe.mirai.Bot;


public class BotSave {
    public static Bot bot = null;
    public static MiraiConfig miraiConfig = null;

    public static Bot getBot() {
        return bot;
    }

    public static void setBot(Bot bot) {
        BotSave.bot = bot;
    }

    public static MiraiConfig getMiraiConfig() {
        return miraiConfig;
    }

    public static void setMiraiConfig(MiraiConfig miraiConfig) {
        BotSave.miraiConfig = miraiConfig;
    }
}
