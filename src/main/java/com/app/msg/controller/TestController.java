package com.app.msg.controller;

import com.app.msg.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * Created by infear on 2017/5/25.
 * Just for debug and test
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/getById")
    Object get(Long id) {
        return id != null ? Arrays.asList(repository.findOne(id)) : repository.findAll();
    }
}
