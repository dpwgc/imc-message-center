package com.dpwgc.message.center.ui.controller.notice;

import com.dpwgc.message.center.app.command.notice.service.information.InformationCommandService;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.notice.information.CreateInformationCommand;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/notice/information")
public class InformationController {

    @Resource
    InformationCommandService informationCommandService;

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
}
