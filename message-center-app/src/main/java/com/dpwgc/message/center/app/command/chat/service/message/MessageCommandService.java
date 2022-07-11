package com.dpwgc.message.center.app.command.chat.service.message;

import com.dpwgc.message.center.sdk.command.chat.message.CreateMessageWsCommand;

public interface MessageCommandService {

    boolean createMessage(CreateMessageWsCommand command,String appId,String groupId,String userId);
}
