package com.dpwgc.message.center.infrastructure.dal.notice.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("imc_notice_information")
public class InformationPO {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 通知信息id
     */
    private String informationId;

    /**
     * 通知信息所属应用id
     */
    private String appId;

    /**
     * 通知信息所属群组id
     */
    private String groupId;

    /**
     * 通知信息所属用户id（谁发的通知）
     */
    private String userId;

    /**
     * 通知信息标题
     */
    private String title;

    /**
     * 通知信息主体内容
     */
    private String content;

    /**
     * 通知信息创建时间戳（毫秒级）
     */
    private Long createTime;

    /**
     * 通知信息删除时间戳（毫秒级）
     */
    private Long deleteTime;

    /**
     * 通知信息点击后的跳转链接
     */
    private String jumpUrl;

    /**
     * 通知信息当前状态（1-正常，0-已删除）
     */
    private Integer status;

    /**
     * 通知信息类型（自定义，例如：1-置顶，2-精华，3-普通）
     */
    private Integer type;
}
