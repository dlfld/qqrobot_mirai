package com.example.miraijava.job;

import net.mamoe.mirai.Bot;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/17 10:17 AM
 * @Version 1.0
 */
public class BotSave {
    public static Bot bot = null;

    public static Bot getBot(){
        return bot;
    }
    public static void setBot(Bot bot){
        BotSave.bot = bot;
    }
}
