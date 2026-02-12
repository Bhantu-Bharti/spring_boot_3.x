package com.walmart.security.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/get")
    public String getHelloWorld(){
        return "Hello World!!!";
    }

    @GetMapping("/create")
    public String createHello(){
        return "Successfully created.";
    }
}