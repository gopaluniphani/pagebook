package com.example.business.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pagebook/api/business/")
public class BusinessController {
    @GetMapping(value = "/sample")
    public String returnSample() {
        return "sample message";
    }
}
