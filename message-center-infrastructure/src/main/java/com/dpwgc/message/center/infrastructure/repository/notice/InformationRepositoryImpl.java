package com.dpwgc.message.center.infrastructure.repository.notice;

import com.dpwgc.message.center.domain.notice.information.InformationRepository;
import com.dpwgc.message.center.sdk.command.notice.information.InformationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationRepositoryImpl implements InformationRepository {

    @Override
    public boolean save(InformationDTO informationDTO) {
        return false;
    }
    @Override
    public boolean delete(String id) {
        return false;
    }
    @Override
    public List<InformationDTO> findByGroupId(String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
        return null;
    }
    @Override
    public List<InformationDTO> findByGroupIdAndType(String groupId, int type, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {
        return null;
    }
}
