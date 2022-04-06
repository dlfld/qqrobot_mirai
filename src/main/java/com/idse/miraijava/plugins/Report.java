package com.idse.miraijava.plugins;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.Plugin;

import net.mamoe.mirai.event.events.MessageEvent;
import net.mamoe.mirai.message.code.MiraiCode;
import net.mamoe.mirai.message.data.*;
import net.mamoe.mirai.utils.ExternalResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;

@Plugin
public class Report {

    @Command(command = "核酸报告")
    public void handleCommand(MessageEvent event,String msg) throws IOException {

    }

}
