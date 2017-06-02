package com.app.msg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by infear on 2017/6/2.
 */
@Configuration
public class ApplicationEventConfig {
    @Bean
    public AppEventPublisher applicationEventPublisher() {
        return new AppEventPublisher();
    }
}
