package com.idse.miraijava.utils;

import com.alibaba.fastjson.JSONObject;
import com.idse.miraijava.pojo.Message;
import com.idse.miraijava.pojo.VaccineMsg;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.DelayQueue;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/22 10:50
 * @Version 1.0
 */
public class HttpRequest {
    private List<Header> getCommonHeader() {
        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Linux; Android 5.1.1; SM-N960F Build/JLS36C; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/74.0.3729.136 Mobile Safari/537.36 MMWEBID/1042 MicroMessenger/7.0.15.1680(0x27000F34) Process/appbrand0 WeChat/arm32 NetType/WIFI Language/zh_CN ABI/arm32"));
        headers.add(new BasicHeader("Referer", "https://servicewechat.com/wxff8cad2e9bf18719/2/page-frame.html"));
        headers.add(new BasicHeader("Accept", "application/json, text/plain, */*"));
        headers.add(new BasicHeader("Host", "miaomiao.scmttec.com"));
        return headers;
    }

    public String get(String path, Header extHeader) throws IOException {
        HttpGet get = new HttpGet(path);
        List<Header> headers = getCommonHeader();
        if (extHeader != null) {
            headers.add(extHeader);
        }
        RequestConfig requestConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(2500)
//                .setSocketTimeout(2500)
//                .setConnectTimeout(2500)
                .build();
        get.setConfig(requestConfig);
        get.setHeaders(headers.toArray(new Header[0]));
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity httpEntity = response.getEntity();
        String json = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
        return json;
//        JSONObject jsonObject = JSONObject.parseObject(json);

//        if ("0000".equals(jsonObject.get("code"))) {
//            return jsonObject.getString("data");
//        }
//        return "";
    }

    public static void main(String[] args) throws IOException, ParseException {
        // 默认的年月日的格式. yyyy-MM-dd
        final String PATTEN_DEFAULT_YMD = "yyyy-MM-dd";
//        // 当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat(PATTEN_DEFAULT_YMD);
//        //获取今天的日期
        String nowDay = sf.format(now);
//        String s = new HttpRequest().get("https://miaomiao.scmttec.com/seckill/seckill/list.do?offset=0&limit=10&regionCode=5101", null);
//        JSONArray jsonArray = JSONObject.parseArray(s);
//        List<VaccineMsg> vaccineMsgs = jsonArray.toJavaList(VaccineMsg.class);
        List<VaccineMsg> vaccineMsgs = new LinkedList<>() {{

            add(new VaccineMsg().setVaccineName("setVaccineName").setName("name").setAddress("adress").setStartTime("2022-03-22 12:28:00"));
            add(new VaccineMsg().setVaccineName("setVaccineName").setName("name").setAddress("adress").setStartTime("2022-03-22 12:38:00"));
            add(new VaccineMsg().setVaccineName("setVaccineName").setName("name").setAddress("adress").setStartTime("2022-03-22 12:48:00"));
            add(new VaccineMsg().setVaccineName("setVaccineName").setName("name").setAddress("adress").setStartTime("2022-03-22 12:58:00"));
            add(new VaccineMsg().setVaccineName("setVaccineName").setName("name").setAddress("adress").setStartTime("2022-03-22 13:00:00"));
        }};
        DelayQueue<Message> delayQueue = new DelayQueue<>();
        for (VaccineMsg vaccineMsg : vaccineMsgs) {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(vaccineMsg.getStartTime());
            //对比的时间
            String day = sf.format(date);
            //如果抢疫苗的时间为今天就加入提醒队列
            if (Objects.equals(day, nowDay)) {
                delayQueue.put(new Message(date, vaccineMsg));
            }
        }

        try {
            while (delayQueue.isEmpty() == false) {
                Message take = delayQueue.take();
                System.out.println("还有10分钟开始抢:\n" + take.getData());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
