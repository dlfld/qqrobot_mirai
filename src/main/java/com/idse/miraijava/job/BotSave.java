package com.idse.miraijava.job;

import net.mamoe.mirai.Bot;


public class BotSave {
    public static Bot bot = null;

    public static Bot getBot(){
        return bot;
    }
    public static void setBot(Bot bot){
        BotSave.bot = bot;
    }
}
