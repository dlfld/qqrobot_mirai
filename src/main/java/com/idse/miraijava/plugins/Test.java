package com.idse.miraijava.plugins;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.idse.miraijava.pojo.HSBG;
import com.idse.miraijava.utils.Base64Util;
import com.idse.miraijava.utils.HttpRequest;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.List;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/25 13:44
 * @Version 1.0
 */
public class Test {
    public static void base64ToFile(String base64, String fileName, String savePath) {
        File file = null;
        //创建文件目录
        String filePath = savePath;
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }
        BufferedOutputStream bos = null;
        java.io.FileOutputStream fos = null;
        try {
            byte[] bytes = Base64.getDecoder().decode(base64);
            file=new File(filePath + fileName);
            fos = new java.io.FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String name = "戴林峰";
        String phone = "13734905487";
        HttpRequest httpRequest = new HttpRequest();
        String hsMsg = httpRequest.get("http://cmlabs.com.cn:8020/Home/Search?name=" + name + "&mzh=" + phone, null);
        System.out.println(hsMsg);
        JSONArray hesuanMsg = JSONObject.parseArray(hsMsg);
        System.out.println(hesuanMsg);
        List<HSBG> hsbgs = hesuanMsg.toJavaList(HSBG.class);
        for (HSBG item : hsbgs) {
            String imageBase64 = httpRequest.get("http://cmlabs.com.cn:8020/Home/GetImage?imgUrl=http:%2F%2Fr.cdcmlabs.com%2FApi%2FCMLABSSePa%2FGetReportImg&type=OTHER&txm=" + item.getTxm() + "&lsh=" + item.getLsh(), null);


        }

    }
}
