package com.dpwgc.message.center.app.command.notice.service.information;

import com.dpwgc.message.center.sdk.model.notice.information.InformationCommand;

public interface InformationCommandService {
    boolean createInformation(InformationCommand informationCommand);
    boolean deleteInformation(String informationId);
}
