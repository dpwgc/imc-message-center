package com.dpwgc.message.center.sdk.model.notice.information;

import lombok.Data;

@Data
public class DeleteInformationCommand {

    /**
     * 要删除的通知信息id
     */
    private String informationId;
}
