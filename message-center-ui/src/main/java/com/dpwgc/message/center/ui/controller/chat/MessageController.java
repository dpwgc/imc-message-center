package com.dpwgc.message.center.ui.controller.chat;

import com.dpwgc.message.center.app.command.chat.service.message.MessageCommandService;
import com.dpwgc.message.center.app.query.chat.message.MessageQueryService;
import com.dpwgc.message.center.domain.chat.message.Message;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.command.chat.message.MessageDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/chat/message")
public class MessageController {

    @Resource
    MessageQueryService messageQueryService;

    @Resource
    MessageCommandService messageCommandService;

    @PutMapping("/recall")
    public ResultDTO<String> recall(String messageId,String recallCause) {
        if (messageCommandService.recall(messageId,recallCause)) {
            return ResultDTO.getSuccessResult("1");
        }
        return ResultDTO.getFailureResult("0");
    }

    @GetMapping("/findByGroupId")
    public ResultDTO<List<MessageDTO>> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        List<MessageDTO> messageDTOList = messageQueryService.findByGroupId(appId,groupId,startTime,endTime,pageNum,pageSize);
        if (messageDTOList != null) {
            return ResultDTO.getSuccessResult(messageDTOList);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByUserId")
    public ResultDTO<List<MessageDTO>> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        List<MessageDTO> messageDTOList = messageQueryService.findByUserId(appId,userId,startTime,endTime,pageNum,pageSize);
        if (messageDTOList != null) {
            return ResultDTO.getSuccessResult(messageDTOList);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByGroupIdAndUserId")
    public ResultDTO<List<MessageDTO>> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        List<MessageDTO> messageDTOList = messageQueryService.findByGroupIdAndUserId(appId,groupId,userId,startTime,endTime,pageNum,pageSize);
        if (messageDTOList != null) {
            return ResultDTO.getSuccessResult(messageDTOList);
        }
        return ResultDTO.getFailureResult("null");
    }

}
