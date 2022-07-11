package com.dpwgc.message.center.ui.controller.chat;

import com.alibaba.fastjson.JSONObject;
import com.dpwgc.message.center.app.handler.RedisEventHandler;
import com.dpwgc.message.center.domain.chat.message.MessageFactory;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.infrastructure.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import com.dpwgc.message.center.domain.chat.message.Message;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 即时通讯-聊天室连接
 */
@ServerEndpoint("/chat/{appId}/{groupId}/{userId}")
@Component
public class MessageWebsocket {

    //Redis工具类（交由IOC自动注入）
    private static RedisUtil redisUtil;

    //Redis订阅监听器设置（交由IOC自动注入）
    private static RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    public void setRepository(RedisUtil redisUtil) {
        MessageWebsocket.redisUtil = redisUtil;
    }

    @Autowired
    public void setRepository(RedisMessageListenerContainer redisMessageListenerContainer) {
        MessageWebsocket.redisMessageListenerContainer = redisMessageListenerContainer;
    }

    //Redis订阅推送消息服务（设为静态，仅在本类加载时调用一次）
    private static final RedisEventHandler redisEventHandler = new RedisEventHandler();

    //记录Redis监听器，避免不同用户连接同一群组时重复创建订阅监听。
    private static final ConcurrentHashMap<String, Boolean> redisListenMap = new ConcurrentHashMap<>();

    /**
     * 建立连接成功调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "appId") String appId, @PathParam(value = "groupId") String groupId,@PathParam(value = "userId") String userId) {

        //会话池的sessionKey由appId+groupId+userId组成
        String sessionKey = appId+groupId+userId;

        /*
         * === 设置监听器，监听在线消息 ===
         */
        //将session与userId传入Redis订阅监听器
        redisEventHandler.setSession(sessionKey,session);

        //设置Redis订阅/发布管道的key，由appId+groupId组成
        //设置Redis订阅/发布监听器，监听推送该群组的消息，redisListenMap避免重复订阅。
        if (redisListenMap.get(appId+groupId) == null) {
            //如果当前这个主题没有被订阅，则建立监听器。
            redisMessageListenerContainer.addMessageListener(redisEventHandler,new PatternTopic(appId+groupId));
            redisListenMap.put(sessionKey,true);
        }

    }

    /**
     * 收到客户端信息
     */
    @OnMessage
    public void onMessage(String content, @PathParam(value = "appId") String appId, @PathParam(value = "groupId") String groupId,@PathParam(value = "userId") String userId) {

        //创建消息模板
        MessageFactory messageFactory = new MessageFactory();
        Message message = messageFactory.create(appId,groupId,userId,content);

        //将Message对象转为json字符串
        String jsonStr = JSONObject.toJSON(message).toString();

        /**
         * 将消息插入mysql或消息队列 TODO
         */

        //在redis中发布消息
        redisUtil.pub("mq:"+groupId,jsonStr);
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        LogUtil.error(throwable.toString());
        //关闭对话
        session.close();
    }
}
