package com.dpwgc.message.center.app.handler;

import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.stream.StreamListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class RedisStreamConsumerHandler implements StreamListener<String, MapRecord<String, String, String>> {

    @Resource
    RedisClient redisClient;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void onMessage(MapRecord<String, String, String> entries) {

        try {
            LogUtil.info(entries.getValue().toString());

            //确认消费，删除消息
            redisClient.delField(applicationName,entries.getId().getValue());
        } catch (Exception e) {
            LogUtil.error(e.toString());
        }
    }
}
