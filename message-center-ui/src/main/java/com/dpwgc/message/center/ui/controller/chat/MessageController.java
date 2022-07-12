package com.dpwgc.message.center.ui.controller.chat;

import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.app.query.chat.message.MessageQueryService;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.chat.message.MessagePageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/chat/message")
public class MessageController {

    @Resource
    MessageQueryService messageQueryService;

    @Resource
    MessageCommandService messageCommandService;

    @PutMapping("/recall")
    public ResultDTO<String> recall(String messageId,String recallCause) {
        if (messageCommandService.recallMessage(messageId,recallCause)) {
            return ResultDTO.getSuccessResult("1");
        }
        return ResultDTO.getFailureResult("0");
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
