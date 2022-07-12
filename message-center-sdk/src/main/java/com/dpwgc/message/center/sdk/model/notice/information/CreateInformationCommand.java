package com.dpwgc.message.center.sdk.model.notice.information;

import lombok.Data;

@Data
public class CreateInformationCommand {

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
     * 通知信息点击后的跳转链接
     */
    private String jumpUrl;

    /**
     * 通知信息类型（自定义，例如：1-置顶，2-精华，3-普通）
     */
    private int type;


}
