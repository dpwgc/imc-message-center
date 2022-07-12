package com.dpwgc.message.center.app.command.chat.service.message;

import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageWsCommand;

public interface MessageCommandService {

    boolean createMessage(CreateMessageWsCommand command,String appId,String groupId,String userId);

    boolean recallMessage(String messageId,String recallCause);
}
