package com.dpwgc.message.center.domain.notice.information;

public interface InformationRepository {
    boolean save(Information information);
    boolean delete(String id);
}
