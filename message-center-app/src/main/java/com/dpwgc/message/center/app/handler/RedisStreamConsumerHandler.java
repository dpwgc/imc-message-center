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
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RedisStreamConsumerHandler implements StreamListener<String, MapRecord<String, String, String>> {

    @Resource
    RedisClient redisClient;

    @Resource
    MessageRepository messageRepository;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.redis.stream.maxRetryCount}")
    private Integer maxRetryCount;

    @Override
    public void onMessage(MapRecord<String, String, String> entries) {

        try {
            //获取消息数据
            String msg = entries.getValue().get("message");
            //获取该消息的投放次数
            int count = Integer.parseInt(entries.getValue().get("count"));

            //序列化字符串
            String msgStr = StringEscapeUtils.unescapeJava(msg);    //这里去除字符串中的转义符号（去除斜杠）
            msgStr = StringUtils.strip(msgStr,"\"\""); //这里去除redis字符串两端的冒号

            Message message = JsonUtil.fromJson(msgStr, Message.class);

            //将消息存入数据库
            if (!messageRepository.save(message)) {

                LogUtil.error("error inserting message into database: "+msgStr);

                //消费失败-重试机制
                if (count >= maxRetryCount) {
                    //超过重试次数，彻底丢弃消息
                    LogUtil.error("removal message: "+msgStr);
                } else {
                    //重新投送消息
                    Map<String,Object> msgRetry = new HashMap<>();
                    msgRetry.put("message",msg);
                    //该消息的投放次数+1
                    msgRetry.put("count",count+1);
                    //重新向stream发送消息
                    redisClient.addStream(applicationName,msgRetry);
                }
            }

            //从stream中删除消息记录
            redisClient.delField(applicationName,entries.getId().getValue());

        } catch (Exception e) {
            LogUtil.error(e.toString());
        }
    }
}
