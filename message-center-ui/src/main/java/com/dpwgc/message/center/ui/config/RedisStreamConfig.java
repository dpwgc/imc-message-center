package com.dpwgc.message.center.ui.config;

import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.dpwgc.message.center.app.handler.RedisStreamConsumerHandler;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.stream.Consumer;
import org.springframework.data.redis.connection.stream.ReadOffset;
import org.springframework.data.redis.connection.stream.RecordId;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.stream.StreamMessageListenerContainer;
import org.springframework.data.redis.stream.Subscription;

import javax.annotation.Resource;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Redis stream配置
 */
@Configuration
public class RedisStreamConfig {

    @Resource
    private RedisStreamConsumerHandler redisStreamConsumerHandler;

    @Resource
    private RedisClient redisClient;

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${cluster.datacenterId}")
    private String datacenterId;

    @Value("${cluster.workerId}")
    private String workerId;

    @Bean
    public Subscription subscription(RedisConnectionFactory factory) throws UnknownHostException {
        StreamMessageListenerContainer.StreamMessageListenerContainerOptions options = StreamMessageListenerContainer
                .StreamMessageListenerContainerOptions
                .builder()
                .pollTimeout(Duration.ofSeconds(1))
                .build();

        //消费者组名称（中台名称 imc-message-center）
        String consumerGroup = applicationName;
        //消费者名称（中台名称+数据中心id+机器id+地址+端口）
        String consumerName = applicationName + "-" + datacenterId + "-" + workerId + "-" + Inet4Address.getLocalHost().getHostAddress() + ":" + port;
        //stream名称（中台名称 imc-message-center）
        String stream = applicationName;
        //初始化stream
        initStream(stream,consumerGroup);

        StreamMessageListenerContainer listenerContainer = StreamMessageListenerContainer.create(factory,options);

        Subscription subscription = listenerContainer.receiveAutoAck(Consumer.from(consumerGroup,consumerName),
                StreamOffset.create(stream, ReadOffset.lastConsumed()), redisStreamConsumerHandler);
        listenerContainer.start();
        return subscription;
    }

    private void initStream(String key, String group){
        //判断key是否存在，如果不存在则创建
        boolean hasKey = redisClient.hasKey(key);
        if(!hasKey){
            Map<String,Object> map = new HashMap<>();
            map.put("message","init");
            RecordId recordId = redisClient.addStream(key, map);
            redisClient.addGroup(key,group);
            //将初始化的值删除掉
            redisClient.delField(key,recordId.getValue());
            LogUtil.info("stream initialize success");
        }
    }
}