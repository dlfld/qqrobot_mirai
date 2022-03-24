package com.idse.miraijava.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author dailinfeng
 * @Description TODO
 * @Date 2022/3/22 11:10
 * @Version 1.0
 */
@Data
@Accessors(chain = true)
public class VaccineMsg {
    String imgUrl;
    String vaccineName;
    String address;
    String name;
    String startTime;


    @Override
    public String toString() {
        return "秒苗：\n" +
                "疫苗名:" + vaccineName + '\n' +
                "医院地址:" + address + '\n' +
                "医院名:" + name + '\n' +
                "秒杀时间:" + startTime + '\n';
    }
}
