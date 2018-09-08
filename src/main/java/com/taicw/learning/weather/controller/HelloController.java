package com.taicw.learning.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by taichangwei on 2018/8/19.
 */
@RestController
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello(){
        return "hello World!";
    }

}
