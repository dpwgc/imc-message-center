package com.dpwgc.message.center.infrastructure.dal.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface InformationMapper extends BaseMapper<InformationPO> {
}
