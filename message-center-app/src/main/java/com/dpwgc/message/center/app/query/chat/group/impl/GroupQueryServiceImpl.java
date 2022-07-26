package com.dpwgc.message.center.app.query.chat.group.impl;

import com.dpwgc.message.center.app.query.chat.group.GroupQueryService;
import com.dpwgc.message.center.sdk.model.chat.group.GroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupQueryServiceImpl implements GroupQueryService {

    public List<GroupDTO> findGroupListByUserId(String appId,String userId) {
        return null;
    }
}
