package com.prog3.walletapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloWorldController {
    @GetMapping({"/hello/{name}", "/hello/", "/"})
    public String helloWorld(@PathVariable(required = false) String name){
        if (name==null){
            return "Hello World";
        } else {
            return "Hello " + name;
        }
    }

    @GetMapping("/api/foos")
    @ResponseBody
    public String getFoos(@RequestParam String id) {
        return "ID: " + id;
    }
}
