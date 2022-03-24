package com.example.miraijava.job;

import com.example.miraijava.handler.MyEventHandlers;
import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.BotEvent;
import net.mamoe.mirai.utils.BotConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/22 13:07
 * @Version 1.0
 */
@Component
@Order(value = 1)
public class StartBot implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        String qq = "3152364256"; // qq号
        String password = "2441086385dlf";//密码
        String filePath = "/Users/dailinfeng/Desktop/小项目/mirai-java/src/main/resources/work_dir"; //workdir的实际路径
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
@Order(value = 2)
class StartMiaoMiao implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
            new TaskJob().miaomiao();
    }
}