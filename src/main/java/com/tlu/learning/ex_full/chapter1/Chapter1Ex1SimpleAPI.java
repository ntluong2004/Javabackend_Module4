package com.tlu.learning.ex_full.chapter1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Chapter1Ex1SimpleAPI {

    @GetMapping("/greeting")
    public String sayHello() {
        return "Hello Luong";
    }
}