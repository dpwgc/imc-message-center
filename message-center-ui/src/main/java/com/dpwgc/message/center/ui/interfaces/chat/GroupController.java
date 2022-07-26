package com.dpwgc.message.center.ui.interfaces.chat;

import com.dpwgc.message.center.app.query.chat.group.GroupQueryService;
import com.dpwgc.message.center.sdk.base.ResultDTO;
import com.dpwgc.message.center.sdk.model.chat.group.GroupDTO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

/**
 * 群组接口
 */
@RestController
@RequestMapping("/chat/group")
public class GroupController {

    @Resource
    GroupQueryService groupQueryService;

    /**
     * 根据用户id返回用户加入的所有群组的最新信息
     * @param appId 应用id
     * @param userId 用户id
     * @return ResultDTO<List<GroupDTO>>
     */
    @GetMapping("/findGroupListByUserId")
    public ResultDTO<List<GroupDTO>> findGroupListByUserId(String appId, String userId) {

        List<GroupDTO> groupDTOList = groupQueryService.findGroupListByUserId(appId,userId);
        if (groupDTOList != null) {
            return ResultDTO.getSuccessResult(groupDTOList);
        }
        return ResultDTO.getFailureResult("null");
    }
}
