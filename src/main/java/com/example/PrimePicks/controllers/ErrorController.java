package com.example.PrimePicks.controllers;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

@ControllerAdvice
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {


    @RequestMapping("/error")
    public String handleError() {
        return "/mainPage";
    }


}


