package com.dpwgc.message.center.sdk.model.notice.information;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DeleteInformationCommand {

    /**
     * 要删除的通知信息id
     */
    @NotNull
    private String informationId;
}
