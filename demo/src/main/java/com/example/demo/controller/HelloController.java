package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @RequestMapping("/greeting")
    public String hello (@RequestParam(defaultValue = "") String name, @RequestParam(defaultValue = "") String address) {
        return String.format("Hello %s!!!", name, address);
    }
}
