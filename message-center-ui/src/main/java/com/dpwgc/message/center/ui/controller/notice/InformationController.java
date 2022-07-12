package com.dpwgc.message.center.ui.controller.notice;

import com.dpwgc.message.center.app.command.notice.service.information.InformationCommandService;
import com.dpwgc.message.center.app.query.notice.information.InformationQueryService;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.notice.information.CreateInformationCommand;
import com.dpwgc.message.center.sdk.model.notice.information.InformationPageDTO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notice/information")
public class InformationController {

    @Resource
    InformationCommandService informationCommandService;

    @Resource
    InformationQueryService informationQueryService;

    @PostMapping("/create")
    public ResultDTO<String> createInformation(@RequestBody CreateInformationCommand createInformationCommand) {
        if (informationCommandService.createInformation(createInformationCommand)) {
            return ResultDTO.getSuccessResult("");
        }
        return ResultDTO.getFailureResult("");
    }

    @PostMapping("/delete")
    public ResultDTO<String> deleteInformation(String informationId) {
        if (informationCommandService.deleteInformation(informationId)) {
            return ResultDTO.getSuccessResult("");
        }
        return ResultDTO.getFailureResult("");
    }

    @GetMapping("/findByGroupId")
    public ResultDTO<InformationPageDTO> findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        InformationPageDTO informationPageDTO = informationQueryService.findByGroupId(appId,groupId,startTime,endTime,pageNum,pageSize);
        if (informationPageDTO != null) {
            return ResultDTO.getSuccessResult(informationPageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByUserId")
    public ResultDTO<InformationPageDTO> findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        InformationPageDTO informationPageDTO = informationQueryService.findByUserId(appId,userId,startTime,endTime,pageNum,pageSize);
        if (informationPageDTO != null) {
            return ResultDTO.getSuccessResult(informationPageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }
    @GetMapping("/findByGroupIdAndUserId")
    public ResultDTO<InformationPageDTO> findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        InformationPageDTO informationPageDTO = informationQueryService.findByGroupIdAndUserId(appId,groupId,userId,startTime,endTime,pageNum,pageSize);
        if (informationPageDTO != null) {
            return ResultDTO.getSuccessResult(informationPageDTO);
        }
        return ResultDTO.getFailureResult("null");
    }
}
