package com.idse.miraijava.pojo;

import lombok.Getter;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @Author dailinfeng
 * @Description 延迟队列
 * @Date 2022/3/22 11:50
 * @Version 1.0
 */
@Getter
@Accessors(chain = true)
public class Message implements Delayed {

    private long nanoTime;

    private VaccineMsg data;

    public Message() {
    }

    public Message(Date skillTime, VaccineMsg data) {

//        设置开抢前的10分钟发送提醒
        this.nanoTime = (skillTime.getTime() - 10 * 1000 * 60);
        Date date = new Date(nanoTime);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sf.format(date));
        this.data = data;
    }

    @Override
    public long getDelay(@NotNull TimeUnit unit) {
        return unit.convert(nanoTime - now(), NANOSECONDS);
    }

    private final long now() {
        return System.currentTimeMillis();
    }

    @Override
    public int compareTo(@NotNull Delayed other) {
        if (other == this) // compare zero if same object
            return 0;
        if (other instanceof Message) {
            Message x = (Message) other;
            long diff = nanoTime - x.nanoTime;
            if (diff < 0)
                return -1;
            else if (diff > 0)
                return 1;
            else
                return 1;
        }
        long diff = getDelay(NANOSECONDS) - other.getDelay(NANOSECONDS);
        return (diff < 0) ? -1 : (diff > 0) ? 1 : 0;
    }
}
