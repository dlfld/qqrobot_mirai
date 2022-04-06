package com.idse.miraijava.job;

import com.idse.miraijava.handler.MyEventHandlers;
import com.idse.miraijava.pojo.MiraiConfig;
import lombok.extern.slf4j.Slf4j;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;

@Component
@Slf4j
public class StartBot {

    public void run(MiraiConfig miraiConfig) throws Exception {
        new Thread(() -> {
            log.info(miraiConfig.toString());
            String qq = miraiConfig.getQq(); // qq号
            String password = miraiConfig.getPassword();//密码
            String filePath = miraiConfig.getWorkDir(); //workdir的实际路径
            Bot bot = BotFactory.INSTANCE.newBot(Long.parseLong(qq), password, new BotConfiguration() {{
                //设置登录协议
                setProtocol(MiraiProtocol.ANDROID_PAD);
                //设置工具目录
                setWorkingDir(new File(filePath));
                //设置cache目录
                setCacheDir(new File("cache"));
                //Bot 默认使用全随机的设备信息。
                fileBasedDeviceInfo("device.json");
            }}).getBot();
            BotSave.setBot(bot);
            bot.login();
            EventChannel channel = GlobalEventChannel.INSTANCE.filter(ev -> ev instanceof BotEvent && ((BotEvent) ev).getBot().getId() == Long.parseLong(qq)); // 筛选来自某一个 Bot 的事件
            channel.registerListenerHost(new MyEventHandlers());
        }).start();
    }
}
