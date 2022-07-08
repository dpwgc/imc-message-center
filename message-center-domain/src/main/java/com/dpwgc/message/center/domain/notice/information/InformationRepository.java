package com.dpwgc.message.center.domain.notice.information;

import com.dpwgc.message.center.sdk.command.notice.information.InformationDTO;

import java.util.List;

public interface InformationRepository {
    boolean save(InformationDTO informationDTO);
    boolean delete(String id);
    List<InformationDTO> findByGroupId(String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
    List<InformationDTO> findByGroupIdAndType(String groupId, int type, Long startTime, Long endTime, Integer pageNum, Integer pageSize);
}
