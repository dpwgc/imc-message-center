package com.dpwgc.message.center.app.query.chat.group;

import com.dpwgc.message.center.sdk.model.chat.group.GroupDTO;

import java.util.List;

public interface GroupQueryService {
    List<GroupDTO> findGroupListByUserId(String appId,String userId);
}
