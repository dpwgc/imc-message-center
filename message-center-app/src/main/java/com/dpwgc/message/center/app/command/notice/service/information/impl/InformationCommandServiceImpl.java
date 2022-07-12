package com.dpwgc.message.center.app.command.notice.service.information.impl;

import com.dpwgc.message.center.app.command.notice.service.information.InformationCommandService;
import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.domain.notice.information.InformationFactory;
import com.dpwgc.message.center.domain.notice.information.InformationRepository;
import com.dpwgc.message.center.infrastructure.util.IdGenUtil;
import com.dpwgc.message.center.sdk.model.notice.information.InformationCommand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InformationCommandServiceImpl implements InformationCommandService {

    @Resource
    InformationRepository informationRepository;

    @Resource
    IdGenUtil idGenUtil;

    public boolean createInformation(InformationCommand informationCommand) {

        InformationFactory informationFactory = new InformationFactory();

        //informationId,appId,groupId,userId,title,content,jumpURL,type
        Information information = informationFactory.create(idGenUtil.nextIdString(),informationCommand.getAppId(),informationCommand.getGroupId(),informationCommand.getUserId(),informationCommand.getTitle(),informationCommand.getContent(),informationCommand.getJumpUrl(),informationCommand.getType());

        return informationRepository.save(information);
    }
    public boolean deleteInformation(String informationId) {
        return informationRepository.delete(informationId);
    }
}
