package com.dpwgc.message.center.ui.controller.chat;

import com.alibaba.fastjson.JSONObject;
import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.app.handler.RedisEventHandler;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.command.chat.message.CreateMessageWsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

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
public class MessageWebSocket {

    private static MessageCommandService messageCommandService;

    //Redis订阅监听器设置（交由IOC自动注入）
    private static RedisMessageListenerContainer redisMessageListenerContainer;

    @Autowired
    public void setRepository(MessageCommandService messageCommandService) {
        MessageWebSocket.messageCommandService = messageCommandService;
    }

    @Autowired
    public void setRepository(RedisMessageListenerContainer redisMessageListenerContainer) {
        MessageWebSocket.redisMessageListenerContainer = redisMessageListenerContainer;
    }

    //Redis订阅推送消息服务（设为静态，仅在本类加载时调用一次）
    private static final RedisEventHandler redisEventHandler = new RedisEventHandler();

    //记录Redis监听器，避免不同用户连接同一群组时重复创建订阅监听。
    private static final ConcurrentHashMap<String, Boolean> redisListenMap = new ConcurrentHashMap<>();

    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private static final ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

    /**
     * 给指定用户发送信息
     */
    public static void sendInfo(String sessionKey, String msg){
        Session session = sessionPools.get(sessionKey);
        try {
            sendMessage(session, msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     */
    public static void sendMessage(Session session, String msg) throws IOException {
        if(session != null){
            synchronized (session) {
                session.getBasicRemote().sendText(msg);
            }
        }
    }

    /**
     * 建立连接成功调用
     */
    @OnOpen
    public void onOpen(Session session, @PathParam(value = "appId") String appId, @PathParam(value = "groupId") String groupId,@PathParam(value = "userId") String userId) {

        //会话池的sessionKey由appId+groupId+userId组成
        String sessionKey = appId.concat("-").concat(groupId).concat("-").concat(userId);

        sessionPools.put(sessionKey, session);//往websocket连接池里添加用户

        /*
         * === 设置监听器，监听在线消息 ===
         */
        //将session与userId传入Redis订阅监听器
        redisEventHandler.setSession(sessionKey,session);

        //设置Redis订阅/发布管道的key（broadcast-{appId}）
        //设置Redis订阅/发布监听器，监听推送该群组的消息
        String redisMQKey = "broadcast-".concat(appId);

        if (redisListenMap.get(redisMQKey) == null) {
            //如果当前这个主题没有被订阅，则建立监听器。
            redisMessageListenerContainer.addMessageListener(redisEventHandler,new PatternTopic(redisMQKey));
            //redisListenMap：记录已订阅的监听管道，避免重复订阅
            redisListenMap.put(redisMQKey,true);
            LogUtil.info("subscribed to redis channel: ".concat(redisMQKey));
        }

        //回应客户端-连接成功
        sendInfo(sessionKey,ResultDTO.getSuccessResult("").toString());
    }

    /**
     * 收到客户端信息
     */
    @OnMessage
    public void onMessage(String msg, @PathParam(value = "appId") String appId, @PathParam(value = "groupId") String groupId,@PathParam(value = "userId") String userId) {

        //JSON字符串转成JSON对象
        JSONObject jsonObject = (JSONObject) JSONObject.parse(msg);

        //JSON对象转换成Java对象
        CreateMessageWsCommand command = JSONObject.toJavaObject(jsonObject, CreateMessageWsCommand.class);

        /**
         * 将消息插入mysql或消息队列 TODO
         */
        String sessionKey = appId.concat("-").concat(groupId).concat("-").concat(userId);
        //在数据层插入消息
        if (messageCommandService.createMessage(command,appId,groupId,userId)) {
            //回应客户端-消息发送成功
            sendInfo(sessionKey,ResultDTO.getSuccessResult("").toString());
        } else {
            //回应客户端-消息发送失败
            sendInfo(sessionKey,ResultDTO.getFailureResult("").toString());
        }
    }

    //错误时调用
    @OnError
    public void onError(Session session, Throwable throwable) throws IOException {
        String err = throwable.toString();
        LogUtil.error(err);
        //回应客户端-连接异常
        sendMessage(session,ResultDTO.getFailureResult(err).toString());
        //关闭对话
        session.close();
    }
}
