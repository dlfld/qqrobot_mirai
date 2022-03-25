package com.idse.miraijava.plugins;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idse.miraijava.annotation.Command;
import com.idse.miraijava.annotation.Plugin;
import com.idse.miraijava.pojo.HSBG;
import com.idse.miraijava.utils.HttpRequest;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.contact.Group;
import net.mamoe.mirai.contact.User;
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

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/24 13:46
 * @Version 1.0
 */
@Plugin
public class Report {

    @Command(command = "核酸报告")
    public void handleCommand(MessageEvent event,String msg) throws IOException {

        Contact subject = event.getSubject();
        String messageInput = event.getMessage().get(1) + "";
        messageInput = messageInput.replace("核酸报告", "");
        String regex = "#";
        String[] split = messageInput.split(regex);
        if (split.length != 2) {
            subject.sendMessage("查询核酸报告请输入： 核酸报告 名字#电话");
            return;
        }
        String name = split[0].strip();
        String phone = split[1].strip();
        HttpRequest httpRequest = new HttpRequest();
        String req1 = httpRequest.get("http://cmlabs.com.cn:8020/Home/Search?name=" + name + "&mzh=" + phone, null);
        JSONArray hesuanMsg = JSONObject.parseArray(req1);
        System.out.println(hesuanMsg);
        List<HSBG> hsbgs = hesuanMsg.toJavaList(HSBG.class);
        for (HSBG item : hsbgs) {
            String imageBase64 = httpRequest.get("http://cmlabs.com.cn:8020/Home/GetImage?imgUrl=http:%2F%2Fr.cdcmlabs.com%2FApi%2FCMLABSSePa%2FGetReportImg&type=OTHER&txm=" + item.getTxm() + "&lsh=" + item.getLsh(), null);
            //下面是发送消息的
            byte[] bytes = Base64.getMimeDecoder().decode(imageBase64);
            Message message = MessageUtils.newChain();
            ExternalResource externalResource = ExternalResource.create(bytes);
            Image image = subject.uploadImage(externalResource);
            message = message.plus(image);
            subject.sendMessage(message);
            externalResource.close();
        }


    }

}
