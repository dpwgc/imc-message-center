package com.dpwgc.messagecenterdomain.chat.message;

import java.util.List;

public interface MessageRepository {
    boolean save(Message message);
    boolean recall(String id);
    List<Message> findByGroupId(String appId,String groupId, Integer pageNum, Integer pageSize);
    List<Message> findByUserId(String appId,String userId,Integer pageNum,Integer pageSize);
    List<Message> findByGroupIdAndUserId(String appId,String groupId,String userId,Integer pageNum,Integer pageSize);
}
