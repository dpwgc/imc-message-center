package com.dpwgc.message.center.infrastructure.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志记录
 */
public class LogUtil {

    /**
     * 日志记录器对象
     */
    private static final Logger LOGGER= LoggerFactory.getLogger(LogUtil.class);


    public static void info(String log) {
        preprocessing(log);
        LOGGER.info(log);
    }

    public static void error(String log) {
        preprocessing(log);
        LOGGER.error(log);
    }

    public static void warn(String log) {
        preprocessing(log);
        LOGGER.warn(log);
    }

    public static void debug(String log) {
        preprocessing(log);
        LOGGER.debug(log);
    }

    public static void trace(String log) {
        preprocessing(log);
        LOGGER.trace(log);
    }

    /**
     * 在日志被写入本地之前的处理程序
     * @param log
     */
    private static void preprocessing(String log) {
        return;
    }
}
