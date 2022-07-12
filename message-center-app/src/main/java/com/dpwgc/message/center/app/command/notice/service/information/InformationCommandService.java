package com.dpwgc.message.center.app.command.notice.service.information;

import com.dpwgc.message.center.sdk.model.notice.information.CreateInformationCommand;

public interface InformationCommandService {
    boolean createInformation(CreateInformationCommand createInformationCommand);
    boolean deleteInformation(String informationId);
}
