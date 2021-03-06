package com.dpwgc.message.center.domain.notice.information;

import org.springframework.stereotype.Component;

/**
 * @author cah
 * @description TODO
 * @date 2021/11/3 10:09 上午
 */
@Component
public class InformationFactory {

    public Information create(String informationId, String appId, String groupId, String userId, String title, String content, String jumpURL, int type) {
        return new Information().create(informationId,appId,groupId,userId,title,content,jumpURL,type);
    }
}
