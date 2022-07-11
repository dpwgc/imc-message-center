package com.dpwgc.message.center.app.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.sql.StringEscape;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
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

        String msg = new String(message.getBody()); //消息内容（MessageDTO - JSON字符串）

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
                //序列化字符串
                String msgStr = StringEscapeUtils.unescapeJava(msg);
                msgStr = StringUtils.strip(msgStr,"\"\""); //这里去除redis字符串两端的冒号

                //JSON字符串转成JSON对象
                JSONObject jsonObject = (JSONObject) JSONObject.parse(StringEscapeUtils.unescapeJava(msgStr));//这里使用StringEscapeUtils去除转义斜杠

                //JSON对象转换成Java对象
                MessageDTO messageDTO = JSONObject.toJavaObject(jsonObject, MessageDTO.class);

                //如果消息与会话属于同一应用&&同一群组
                if (messageDTO.getAppId().equals(session.getPathParameters().get("appId")) && messageDTO.getGroupId().equals(session.getPathParameters().get("groupId"))) {
                    synchronized (session) {
                        //推送消息
                        session.getBasicRemote().sendText(JSON.parse(msg).toString());
                    }
                }
            } catch (IOException e) {
                LogUtil.error(e.toString());
            }
        }
    }
}