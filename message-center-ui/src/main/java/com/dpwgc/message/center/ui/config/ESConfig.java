package com.dpwgc.message.center.ui.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * ElasticSearch 客户端配置
 */
@Configuration
public class ESConfig {

    //Elasticsearch连接地址
    @Value("${spring.elasticsearch.uris}")
    private String uris;

    //Elasticsearch账户
    @Value("${spring.elasticsearch.username}")
    private String username;

    //Elasticsearch密码
    @Value("${spring.elasticsearch.password}")
    private String password;

    //要使用的elasticsearch索引名称
    @Value("${spring.elasticsearch.indexName}")
    private String indexName;

    @Bean
    public String indexName(){
        return indexName;
    }
}
