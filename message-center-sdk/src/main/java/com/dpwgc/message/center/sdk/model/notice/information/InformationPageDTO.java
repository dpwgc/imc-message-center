package com.dpwgc.message.center.sdk.model.notice.information;

import lombok.Data;

import java.util.List;

@Data
public class InformationPageDTO {
    /**
     * 指定时间段内的通知信息总数
     */
    private Long total;
    /**
     * 当前分页内的通知信息列表
     */
    private List<InformationDTO> pageInformationList;
}
