package com.yi.demo.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 */
@RestController
@RequestMapping("/test")
public class HelloController {

    /**
     * greet
     *
     * @return string
     */
    @GetMapping("/greet")
    public String great() {
        return "Hi~, Who're you?";
    }

}
