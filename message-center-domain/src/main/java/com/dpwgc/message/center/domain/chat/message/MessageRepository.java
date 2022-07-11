package com.dpwgc.message.center.domain.chat.message;

public interface MessageRepository {
    boolean save(Message message);
    Message recall(String messageId,String recallCause);
}
