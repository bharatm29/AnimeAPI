package com.bharat.AnimeAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimeController {
    @GetMapping("/test")
    public String testEndpoint(){
        return "OK!!";
    }
}