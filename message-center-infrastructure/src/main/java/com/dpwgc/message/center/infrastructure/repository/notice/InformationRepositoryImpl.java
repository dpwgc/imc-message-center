package com.dpwgc.message.center.infrastructure.repository.notice;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dpwgc.message.center.domain.notice.information.Information;
import com.dpwgc.message.center.domain.notice.information.InformationRepository;
import com.dpwgc.message.center.infrastructure.assembler.InformationPOAssembler;
import com.dpwgc.message.center.infrastructure.assembler.MessagePOAssembler;
import com.dpwgc.message.center.infrastructure.dal.chat.entity.MessagePO;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import com.dpwgc.message.center.infrastructure.dal.notice.mapper.InformationMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class InformationRepositoryImpl implements InformationRepository {

    @Resource
    InformationMapper informationMapper;

    @Override
    public boolean save(Information information) {

        InformationPO informationPO = InformationPOAssembler.INSTANCE.assemblerInformationPO(information);
        return informationMapper.insert(informationPO) > 0;
    }
    @Override
    public boolean delete(String informationId) {

        QueryWrapper<InformationPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("information_id",informationId);
        queryWrapper.eq("status",1);
        InformationPO informationPO = informationMapper.selectOne(queryWrapper);

        if (informationPO == null) {
            return false;
        }

        informationPO.setStatus(0);
        informationPO.setDeleteTime(System.currentTimeMillis());

        return informationMapper.update(informationPO,queryWrapper) > 0;
    }
}
