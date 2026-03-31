package com.tlu.learning.ex_full.chapter1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Chapter1Ex3Calculator {

    @GetMapping("/calculator")
    public ResponseEntity<String> calculate(
            @RequestParam(value = "firstNumber", required = false) Double first,
            @RequestParam(value = "secondNumber", required = false) Double second,
            @RequestParam(value = "operator", required = false) String operator) {

        if (first == null || second == null || operator == null) {
            return ResponseEntity.badRequest().body("Error: Missing parameters (firstNumber, secondNumber, operator).");
        }

        double result = 0;

        switch (operator) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                if (second == 0) {
                    return ResponseEntity.badRequest().body("Error: Division by zero is not allowed.");
                }
                result = first / second;
                break;
            default:
                return ResponseEntity.badRequest().body("Error: Invalid operator. Please use +, -, *, or /.");
        }

        return ResponseEntity.ok("Result: " + result);
    }
}