package com.app.msg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by infear on 2017/5/25.
 * Simple Msg App Entry Point
 */
@ComponentScan()
@EnableAutoConfiguration
@EnableJpaRepositories()
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
