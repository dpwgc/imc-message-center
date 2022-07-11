package com.dpwgc.message.center.app.command.chat.service.message;

import com.dpwgc.message.center.sdk.command.chat.message.CreateMessageWsCommand;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;

public interface MessageService {

    public boolean createMessage(CreateMessageWsCommand command,String appId,String groupId,String userId);
}
