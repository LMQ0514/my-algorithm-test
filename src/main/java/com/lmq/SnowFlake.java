package com.lmq;
import java.util.Date;

/**
 * 雪花算法，生成唯一id
 */
public class SnowFlake {
    //工作机器id
    private long workId;
    //数据中心id
    private long datacenterId;
    //毫秒内序列号
    private long sequence = 0l;
    //上次生成时间戳
    private long lastTimestamp = -1l;
    //开始时间戳2021-1-1
    private final long twepoch = 1609430400000L;
    //工作机器id位数
    private final long workIdBits = 5l;
    //数据中心id位数
    private final long datacenterIdBits = 5l;
    //工作机器最大id
    private final long maxWorkId = -1l ^ (-1l << workIdBits);
    //数据中心最大id
    private final long maxDatacenterId = -1l ^ (-1l << datacenterIdBits);
    //序列号位数
    private long sequenceBits = 12l;
    //毫秒内最大序列号
    private final long maxSequence = -1l ^ (-1l << sequenceBits);
    //workId左移量
    private long workIdLeftShift = datacenterIdBits + sequenceBits;
    //datacenterId左移量
    private long datacenterIdLeftShift = sequenceBits;
    //时间戳左移量
    private long timeStampLeftShift = workIdBits + datacenterIdBits + sequenceBits;
    //检查机房id，数据中心id不能超出限制
    public SnowFlake(long workId,long datacenterId){
        if(workId > maxWorkId || workId < 0){
            throw new IllegalArgumentException("worlId 太大了");
        }
        if(datacenterId > maxDatacenterId || datacenterId < 0){
            throw new IllegalArgumentException("datacenterId 太大了");
        }
        this.workId = workId;
        this.datacenterId = datacenterId;
    }

    public synchronized long nextId(){
        Long timeStamp = new Date().getTime();
        //得到的时间戳小于上次生成时间戳
        if(timeStamp < lastTimestamp){
            throw new RuntimeException("时间已经调整过");
        }

        //得到的时间戳等于上次生成时间戳
        if(timeStamp == lastTimestamp){
            //在同一毫秒中，判断sequence是否超出范围
            //sequence自增,并且不超过最大值
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0){
                //避免sequence超过范围
                timeStamp = tilNextMillis(lastTimestamp);
            }
        }else {
            //说明不在同一毫秒
            sequence = 0l;
        }
        //记录最后生成时间戳
        lastTimestamp = timeStamp;

        return ((timeStamp << timeStampLeftShift) |
                (workId << workIdLeftShift) |
                (datacenterId << datacenterIdLeftShift) | sequence);
    }

    public long tilNextMillis(long lastTimestamp){
        Long timeStamp = new Date().getTime();
        while (timeStamp <= lastTimestamp){
            timeStamp = new Date().getTime();
        }
        return timeStamp;
    }

    public static void main(String[] args)  {
        SnowFlake snowFlake = new SnowFlake(1l, 1l);
        for (int i = 0;i < 50;i++){
            long l = snowFlake.nextId();
            System.out.println(l + "位数：" + String.valueOf(l).length());
        }
    }
}
