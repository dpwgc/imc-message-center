package com.dpwgc.message.center.domain.notice.information;

import com.dpwgc.message.center.sdk.command.notice.information.InformationDTO;

import java.util.List;

public interface InformationRepository {
    void save(InformationDTO informationDTO);
    void delete(String id);
    List<InformationDTO> findByGroupId(String groupId, Integer pageNum, Integer pageSize);
    List<InformationDTO> findByGroupIdAndType(String groupId, int type, Integer pageNum, Integer pageSize);
}
