package com.dpwgc.message.center.sdk.command.notice.information;

public class InformationDTO {
    /**
     * 通知信息id
     */
    private String id;

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
    private long createTime;

    /**
     * 通知信息删除时间戳（毫秒级）
     */
    private long deleteTime;

    /**
     * 通知信息点击后的跳转链接
     */
    private String jumpURL;

    /**
     * 通知信息当前状态（1-正常，0-已删除）
     */
    private int status;

    /**
     * 通知信息类型（自定义，例如：1-置顶，2-精华，3-普通）
     */
    private int type;

    protected InformationDTO create(String appId, String groupId, String userId, String title, String content, String jumpURL, int type) {

        this.createTime = System.currentTimeMillis();
        this.status = 1;

        this.appId = appId;
        this.groupId = groupId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.jumpURL = jumpURL;
        this.type = type;

        return this;
    }
}
