package com.example.fullstackproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class HomeController {

    @Autowired
    JdbcTemplate jdbctempplate;

    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("test")
    public String test(){
        String sql = "INSERT INTO accounts(username, password) VALUES ('test', 'test2')";
        jdbctempplate.update(sql);
        return "/index";
    }

}
