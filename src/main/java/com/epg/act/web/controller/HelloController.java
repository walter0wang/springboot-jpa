package com.epg.act.web.controller;

import com.epg.act.web.responseVo.ResultBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public ResultBean<String> index() {
        System.out.printf("");

        return new ResultBean<String>("Greetings from Spring Boot!");
    }

}
