package com.dpwgc.message.center.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@ComponentScan("com.dpwgc.message.center.app")
@ComponentScan("com.dpwgc.message.center.ui.config")
@ComponentScan("com.dpwgc.message.center.infrastructure.util")
@ComponentScan("com.dpwgc.message.center.infrastructure.component")
@ComponentScan("com.dpwgc.message.center.infrastructure.repository")
@MapperScan("com.dpwgc.message.center.infrastructure.dal")
@SpringBootApplication
public class MessageCenterApplication {

    /**
     * 让@Document(indexName = "xxx")能读取到配置文件中的字段
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {

        PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
        c.setIgnoreUnresolvablePlaceholders(true);
        return c;
    }

    public static void main(String[] args) {
        SpringApplication.run(MessageCenterApplication.class, args);
    }

}
