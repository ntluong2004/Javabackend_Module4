package com.tlu.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
class HelloControllerRestController {
    @GetMapping("/hello") //API, endpoint
    public String greeting(@RequestParam(defaultValue = "") String name,
                           @RequestParam(defaultValue = "") String address) {
        return String.format("Hello, %s%s", name, "".equals(address) ? "" : " - " + address);
    }
}

