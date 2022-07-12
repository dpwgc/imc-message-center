package com.dpwgc.message.center.app.query.notice.information;

import com.dpwgc.message.center.sdk.model.notice.information.InformationPageDTO;

public interface InformationQueryService {

    InformationPageDTO findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    InformationPageDTO findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    InformationPageDTO findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
}
