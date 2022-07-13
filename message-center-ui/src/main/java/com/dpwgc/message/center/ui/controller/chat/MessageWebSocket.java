package com.dpwgc.message.center.ui.controller.chat;

import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.app.handler.RedisEventHandler;
import com.dpwgc.message.center.infrastructure.util.JsonUtil;
import com.dpwgc.message.center.infrastructure.util.LogUtil;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageWsCommand;
import com.fasterxml.jackson.core.JsonProcessingException;
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
 * 消息推送-与网关建立websocket连接
 */
@ServerEndpoint("/chat/message/broadcast/{appId}/{gatewayId}")
@Component
public class MessageWebSocket {

    //消息写入服务（交由IOC自动注入）
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
    public void onOpen(Session session, @PathParam(value = "appId") String appId, @PathParam(value = "gatewayId") String gatewayId) {

        //会话池的sessionKey由appId+gatewayId组成（每个网关都与消息中台维持一个session）
        String sessionKey = appId.concat("-").concat(gatewayId);

        sessionPools.put(sessionKey, session);//往websocket连接池里添加连接

        if (!createListener(sessionKey,appId,session)) {
            //回应网关-连接失败
            sendInfo(sessionKey,ResultDTO.getSuccessResult("connection failed").setCode(4000).toString());
        }

        //回应网关-连接成功
        sendInfo(sessionKey,ResultDTO.getSuccessResult("connection successful").setCode(2000).toString());
    }

    /**
     * 收到客户端信息
     */
    @OnMessage
    public void onMessage(String msg, @PathParam(value = "appId") String appId, @PathParam(value = "gatewayId") String gatewayId) throws JsonProcessingException {

        //JSON对象转换成Java对象
        CreateMessageWsCommand command = JsonUtil.fromJson(msg,CreateMessageWsCommand.class);

        //会话池的sessionKey由appId+gatewayId组成
        String sessionKey = appId.concat("-").concat(gatewayId);

        /**
         * 将消息插入mysql或消息队列 TODO
         */
        if (!messageCommandService.createMessage(command)) {
            //回应网关-消息发送失败
            sendInfo(sessionKey,ResultDTO.getFailureResult(msg).setCode(4001).toString());
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

    /**
     * 创建Redis监听器
     * @param sessionKey 连接会话id（中台与网关的连接）
     * @param appId 应用id
     * @param session 会话
     * @return boolean
     */
    private boolean createListener(String sessionKey,String appId,Session session) {

        try {
            /*
             * === 设置监听器，监听在线消息 ===
             */
            //将session传入Redis事件处理器（用于接收广播消息）
            redisEventHandler.setSession(sessionKey,session);

            //设置Redis订阅/发布管道的key（broadcast-{appId}）
            //设置Redis订阅/发布监听器，监听推送到该应用的所有消息
            String redisChannelKey = "broadcast-".concat(appId);

            if (redisListenMap.get(redisChannelKey) == null) {
                //如果当前这个主题没有被订阅，则建立订阅监听器。
                redisMessageListenerContainer.addMessageListener(redisEventHandler,new PatternTopic(redisChannelKey));
                //redisListenMap：记录已订阅的监听管道，避免重复订阅
                redisListenMap.put(redisChannelKey,true);
                LogUtil.info("subscribed to redis channel: ".concat(redisChannelKey));
            }
            return true;
        } catch (Exception e) {
            LogUtil.error(e.toString());
            return false;
        }
    }
}
