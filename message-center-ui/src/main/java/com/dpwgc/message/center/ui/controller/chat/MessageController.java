package com.dpwgc.message.center.ui.controller.chat;

import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.app.query.chat.message.MessageQueryService;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.chat.message.CreateMessageCommand;
import com.dpwgc.message.center.sdk.model.chat.message.MessagePageDTO;
import com.dpwgc.message.center.sdk.model.chat.message.RecallMessageCommand;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat/message")
public class MessageController {

    @Resource
    MessageQueryService messageQueryService;

    @Resource
    MessageCommandService messageCommandService;

    @PostMapping("/create")
    public ResultDTO<String> createMessage(@Validated @RequestBody CreateMessageCommand command) {

        if (messageCommandService.createMessage(command)) {
            return ResultDTO.getSuccessResult("");
        }
        return ResultDTO.getFailureResult("");
    }

    @PostMapping("/recall")
    public ResultDTO<String> recallMessage(@Validated @RequestBody RecallMessageCommand command) {
        if (messageCommandService.recallMessage(command.getMessageId(), command.getRecallCause())) {
            return ResultDTO.getSuccessResult("");
        }
        return ResultDTO.getFailureResult("");
    }

    @GetMapping("/findByGroupId")
    public ResultDTO<MessagePageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        MessagePageDTO messagePageDTO = messageQueryService.findByGroupId(appId,groupId,startTime,endTime,pageNum,pageSize);
        if (messagePageDTO != null) {
            return ResultDTO.getSuccessResult(messagePageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByUserId")
    public ResultDTO<MessagePageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        MessagePageDTO messagePageDTO = messageQueryService.findByUserId(appId,userId,startTime,endTime,pageNum,pageSize);
        if (messagePageDTO != null) {
            return ResultDTO.getSuccessResult(messagePageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByGroupIdAndUserId")
    public ResultDTO<MessagePageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        MessagePageDTO messagePageDTO = messageQueryService.findByGroupIdAndUserId(appId,groupId,userId,startTime,endTime,pageNum,pageSize);
        if (messagePageDTO != null) {
            return ResultDTO.getSuccessResult(messagePageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }

}
