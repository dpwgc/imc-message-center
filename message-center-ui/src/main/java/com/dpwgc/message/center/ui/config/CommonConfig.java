package com.dpwgc.message.center.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Http基础配置
 */
@Configuration
public class CommonConfig {

    @Bean
    RestTemplate restTemplate(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter1());
        return restTemplate;
    }

    public class MappingJackson2HttpMessageConverter1 extends MappingJackson2HttpMessageConverter {
        public MappingJackson2HttpMessageConverter1(){
            List<MediaType> mediaTypes = new ArrayList<>();
            mediaTypes.add(MediaType.TEXT_PLAIN);
            setSupportedMediaTypes(mediaTypes);
        }
    }
}
