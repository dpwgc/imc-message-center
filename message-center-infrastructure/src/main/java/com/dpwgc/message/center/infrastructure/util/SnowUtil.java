package com.dpwgc.message.center.infrastructure.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 雪花算法
 */
@Component
public class SnowUtil implements ApplicationContextAware {

    private static ApplicationContext _applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SnowUtil._applicationContext = applicationContext;
    }

    public static SnowUtil getBean() {
        return _applicationContext.getBean(SnowUtil.class);
    }

    /**
     * 起始的时间戳
     */
    private final long twepoch = 1557825652094L;

    /**
     * 每一部分占用的位数
     */
    private final long workerIdBits = 5L;
    private final long datacenterIdBits = 5L;
    private final long sequenceBits = 12L;

    /**
     * 每一部分的最大值
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    private final long maxSequence = -1L ^ (-1L << sequenceBits);

    /**
     * 每一部分向左的位移
     */
    private final long workerIdShift = sequenceBits;
    private final long datacenterIdShift = sequenceBits + workerIdBits;
    private final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    @Value("${snowflake.datacenterId}")
    private long datacenterId; // 数据中心ID

    @Value("${snowflake.workerId}")
    private long workerId; // 机器ID

    private long sequence = 0L; // 序列号
    private long lastTimestamp = -1L; // 上一次时间戳

    @PostConstruct
    public void init() {
        String msg;
        if (workerId > maxWorkerId || workerId < 0) {
            msg = String.format("worker Id can't be greater than %d or less than 0", maxWorkerId);
            LogUtil.error(msg);
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            msg = String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId);
            LogUtil.error(msg);
        }
    }

    public String nextIdString() {
        long id = nextId();
        return String.valueOf(id);
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            String msg = String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp);
            LogUtil.error(msg);
        }
        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & maxSequence;
            if (sequence == 0L) {
                timestamp = tilNextMillis();
            }
        } else {
            sequence = 0L;
        }
        lastTimestamp = timestamp;

        return (timestamp - twepoch) << timestampShift // 时间戳部分
                | datacenterId << datacenterIdShift // 数据中心部分
                | workerId << workerIdShift // 机器标识部分
                | sequence; // 序列号部分
    }

    private long tilNextMillis() {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
