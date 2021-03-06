package com.dpwgc.message.center.app.query.notice.information.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dpwgc.message.center.app.assembler.InformationAssembler;
import com.dpwgc.message.center.app.query.notice.information.InformationQueryService;
import com.dpwgc.message.center.infrastructure.dal.notice.entity.InformationPO;
import com.dpwgc.message.center.infrastructure.dal.notice.mapper.InformationMapper;
import com.dpwgc.message.center.sdk.model.notice.information.InformationDTO;
import com.dpwgc.message.center.sdk.model.notice.information.InformationPageDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class InformationQueryServiceImpl implements InformationQueryService {

    @Resource
    InformationMapper informationMapper;

    @Override
    public InformationPageDTO findByGroupId(String appId, String groupId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<InformationPO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<InformationPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",appId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<
        queryWrapper.eq("status",1);

        queryWrapper.orderByDesc("create_time");    //按时间倒序查找通知信息

        Page<InformationPO> informationPOPage = informationMapper.selectPage(page,queryWrapper);
        Long count = informationMapper.selectCount(queryWrapper);

        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (int i=0;i<informationPOPage.getRecords().size();i++) {
            InformationDTO informationDTO = InformationAssembler.INSTANCE.assembleInformationDTO(informationPOPage.getRecords().get(i));
            informationDTOList.add(informationDTO);
        }

        InformationPageDTO informationPageDTO = new InformationPageDTO();
        informationPageDTO.setTotal(count);
        informationPageDTO.setPageInformationList(informationDTOList);

        return informationPageDTO;
    }
    @Override
    public InformationPageDTO findByUserId(String appId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<InformationPO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<InformationPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<
        queryWrapper.eq("status",1);

        queryWrapper.orderByDesc("create_time");    //按时间倒序查找通知信息

        Page<InformationPO> informationPOPage = informationMapper.selectPage(page,queryWrapper);
        Long count = informationMapper.selectCount(queryWrapper);

        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (int i=0;i<informationPOPage.getRecords().size();i++) {
            InformationDTO informationDTO = InformationAssembler.INSTANCE.assembleInformationDTO(informationPOPage.getRecords().get(i));
            informationDTOList.add(informationDTO);
        }

        InformationPageDTO informationPageDTO = new InformationPageDTO();
        informationPageDTO.setTotal(count);
        informationPageDTO.setPageInformationList(informationDTOList);

        return informationPageDTO;
    }
    @Override
    public InformationPageDTO findByGroupIdAndUserId(String appId, String groupId, String userId, Long startTime, Long endTime, Integer pageNum, Integer pageSize) {

        Page<InformationPO> page = new Page<>(pageNum, pageSize);
        QueryWrapper<InformationPO> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("app_id",appId);
        queryWrapper.eq("group_id",appId);
        queryWrapper.eq("user_id",userId);
        queryWrapper.ge("create_time",startTime);       //>=
        queryWrapper.lt("create_time",endTime);         //<
        queryWrapper.eq("status",1);

        queryWrapper.orderByDesc("create_time");    //按时间倒序查找通知信息

        Page<InformationPO> informationPOPage = informationMapper.selectPage(page,queryWrapper);
        Long count = informationMapper.selectCount(queryWrapper);

        List<InformationDTO> informationDTOList = new ArrayList<>();
        for (int i=0;i<informationPOPage.getRecords().size();i++) {
            InformationDTO informationDTO = InformationAssembler.INSTANCE.assembleInformationDTO(informationPOPage.getRecords().get(i));
            informationDTOList.add(informationDTO);
        }

        InformationPageDTO informationPageDTO = new InformationPageDTO();
        informationPageDTO.setTotal(count);
        informationPageDTO.setPageInformationList(informationDTOList);

        return informationPageDTO;
    }
}
