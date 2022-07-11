package com.dpwgc.message.center.ui;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dpwgc.message.center.app")
@ComponentScan("com.dpwgc.message.center.ui.config")
@ComponentScan("com.dpwgc.message.center.infrastructure.util")
@ComponentScan("com.dpwgc.message.center.infrastructure.repository")
@MapperScan("com.dpwgc.message.center.infrastructure.dal")
@SpringBootApplication
public class MessageCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageCenterApplication.class, args);
    }

}
