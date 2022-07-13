package com.dpwgc.message.center.app.handler;

import com.dpwgc.message.center.infrastructure.util.JsonUtil;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.chat.message.MessageDTO;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Redis订阅发布事件处理器
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
                //LogUtil.info(msg);
                //序列化字符串
                String msgStr = StringEscapeUtils.unescapeJava(msg);    //这里去除字符串中的转义符号（去除斜杠）
                msgStr = StringUtils.strip(msgStr,"\"\""); //这里去除redis字符串两端的冒号

                //JSON字符串转换成Java对象
                MessageDTO messageDTO = JsonUtil.fromJson(msgStr,MessageDTO.class);

                //如果消息与会话属于同一应用
                if (messageDTO.getAppId().equals(session.getPathParameters().get("appId"))) {
                    synchronized (session) {
                        //如果该消息的状态为0，说明这是撤回消息
                        if (messageDTO.getStatus() == 0) {
                            //推送消息给该应用的所有网关（消息code设为2002，告知客户端这是要撤回的消息）
                            session.getBasicRemote().sendText(ResultDTO.getSuccessResult(messageDTO).setCode(2002).toString());
                        } else {
                            //推送消息给该应用的所有网关（消息code设为2001，表示该消息是正常聊天消息）
                            session.getBasicRemote().sendText(ResultDTO.getSuccessResult(messageDTO).setCode(2001).toString());
                        }
                    }
                }
            } catch (IOException e) {
                LogUtil.error(e.toString());
            }
        }
    }
}