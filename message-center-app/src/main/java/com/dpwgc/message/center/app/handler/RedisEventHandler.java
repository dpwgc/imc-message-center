package com.dpwgc.message.center.app.handler;

import com.alibaba.fastjson.JSON;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Redis订阅发布监听器
 */
@Component
public class RedisEventHandler implements MessageListener {

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocket session对象。
    private static final ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    //插入新的session
    public void setSession(String sessionKey,Session session) {
        sessionPools.put(sessionKey,session);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String content = new String(message.getBody()); //消息内容（JSON字符串）
        String topic = new String(pattern);         //消息主题（appId+groupId+userId）

        //遍历当前在线的会话key列表
        for (String sessionKey: sessionPools.keySet()) {

            //根据key获取value
            Session session = sessionPools.get(sessionKey);

            //如果会话不存在或者已关闭
            if(null == session || !session.isOpen()) {
                //删除并跳过
                sessionPools.remove(sessionKey);
                continue;
            }

            try {
                //如果消息与会话属于同一应用同一群组（topic = appId + groupId）
                if((session.getPathParameters().get("appId")+session.getPathParameters().get("groupId")).equals(topic)){
                    synchronized (session) {
                        //推送消息
                        session.getBasicRemote().sendText(JSON.parse(content).toString());
                    }
                }
            } catch (IOException e) {
                LogUtil.error(e.toString());
            }
        }
    }
}