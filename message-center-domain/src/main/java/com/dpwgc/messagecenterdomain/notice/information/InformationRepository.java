package com.dpwgc.messagecenterdomain.notice.information;

import java.util.List;

public interface InformationRepository {
    void save(Information information);
    void delete(String id);
    List<Information> findByGroupId(String groupId,Integer pageNum,Integer pageSize);
    List<Information> findByGroupIdAndType(String groupId,int type,Integer pageNum,Integer pageSize);
}
