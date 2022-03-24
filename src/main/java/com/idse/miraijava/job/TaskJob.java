package com.idse.miraijava.job;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idse.miraijava.pojo.Message;
import com.idse.miraijava.pojo.VaccineMsg;
import com.idse.miraijava.utils.HttpRequest;
import net.mamoe.mirai.Bot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.DelayQueue;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/1/17 10:13 AM
 * @Version 1.0
 */
@Component("taskJob")
public class TaskJob {

    //    @Scheduled(cron = "* * * * * ? ") // 定时任务表达式 http://cron.ciding.cc/这个网站生成
    public void job1() {
        Bot bot = BotSave.getBot();
        //  第一个是群号 第二个是 n内容
        bot.getGroup(Long.parseLong("950477821")).sendMessage("aaa");
    }

    //    @Scheduled(cron = "* * * * * ? ") // 定时任务表达式
    public void job2() {
        Bot bot = BotSave.getBot();
        //  第一个是好友qq号 第二个是 n内容
        bot.getFriend(Long.parseLong("2441086385")).sendMessage("aaa");
    }

    @Scheduled(cron = "0 0 8 ? * ? ")
    public void miaomiao() throws IOException, ParseException {
        Bot bot = BotSave.getBot();
        // 当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        //获取今天的日期
        String nowDay = sf.format(now);
        String s = new HttpRequest().get("https://miaomiao.scmttec.com/seckill/seckill/list.do?offset=0&limit=10&regionCode=5101", null);
        JSONArray jsonArray = JSONObject.parseArray(s);
        List<VaccineMsg> vaccineMsgs = jsonArray.toJavaList(VaccineMsg.class);
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        for (VaccineMsg vaccineMsg : vaccineMsgs) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vaccineMsg.getStartTime());
            //对比的时间
            String day = sf.format(date);
            //如果抢疫苗的时间为今天就加入提醒队列
            if (Objects.equals(day, nowDay)) {
                delayQueue.put(new Message(date, vaccineMsg));
            }
//            发送当日的提醒
            bot.getGroup(Long.parseLong("733108782")).sendMessage(vaccineMsg.toString());
        }

        try {
            while (delayQueue.isEmpty() == false) {
                Message take = delayQueue.take();
                bot.getGroup(Long.parseLong("733108782")).sendMessage("还有10分钟开始抢:\n" + take.getData().toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
