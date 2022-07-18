package com.dpwgc.message.center.app.handler;

import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.domain.chat.message.MessageRepository;
import com.dpwgc.message.center.infrastructure.component.RedisClient;
import com.dpwgc.message.center.infrastructure.util.JsonUtil;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
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

    @Resource
    MessageRepository messageRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public void onMessage(MapRecord<String, String, String> entries) {

        try {
            //获取消息数据
            String msg = entries.getValue().get("message");

            //序列化字符串
            String msgStr = StringEscapeUtils.unescapeJava(msg);    //这里去除字符串中的转义符号（去除斜杠）
            msgStr = StringUtils.strip(msgStr,"\"\""); //这里去除redis字符串两端的冒号

            Message message = JsonUtil.fromJson(msgStr, Message.class);

            //将消息存入数据库
            messageRepository.save(message);

            //确认消费，删除消息
            redisClient.delField(applicationName,entries.getId().getValue());

        } catch (Exception e) {
            LogUtil.error(e.toString());
        }
    }
}
