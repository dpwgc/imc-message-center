package com.dpwgc.message.center.infrastructure.repository.notice;

import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.domain.notice.information.InformationRepository;
import org.springframework.stereotype.Service;

@Service
public class InformationRepositoryImpl implements InformationRepository {

    @Override
    public boolean save(Information information) {
        return false;
    }
    @Override
    public boolean delete(String id) {
        return false;
    }
}
