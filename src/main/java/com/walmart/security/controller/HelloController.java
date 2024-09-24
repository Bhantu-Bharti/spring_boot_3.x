package com.walmart.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/get")
    public String getHelloWorld(){
        return "Hello World!!!";
    }

    @PostMapping("/create")
    public String createHello(){
        return "Successfully created.";
    }
}
