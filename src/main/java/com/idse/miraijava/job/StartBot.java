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
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/22 13:07
 * @Version 1.0
 */
@Component
@Order(value = 1)
@Slf4j
public class StartBot implements CommandLineRunner {
    @Resource
    MiraiConfig miraiConfig;

    @Override
    public void run(String... args) throws Exception {
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

    }
}

//@Component
@Order(value = 3)
class StartMiaoMiao implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        new TaskJob().miaomiao();
    }
}