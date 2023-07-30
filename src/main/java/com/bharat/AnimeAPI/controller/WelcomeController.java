package com.bharat.AnimeAPI.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
    @GetMapping("/")
    public String welcomePage(){
        return "index";
    }

    @GetMapping("/error")
    public String errorPage(HttpServletRequest request, Model model){
        return "error";
    }
}
