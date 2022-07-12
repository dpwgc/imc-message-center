package com.dpwgc.message.center.sdk.model.notice.information;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class DeleteInformationCommand {

    /**
     * 要删除的通知信息id
     */
    @NotNull
    private String informationId;
}
