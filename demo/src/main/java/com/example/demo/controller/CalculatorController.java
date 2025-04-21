package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {
    @GetMapping("/calculator")
    public ResponseEntity<String> calculate(
            @RequestParam(value = "firstNumber", defaultValue = "") String firstNumberStr,
            @RequestParam(value = "secondNumber", defaultValue = "") String secondNumberStr,
            @RequestParam(value = "operator", defaultValue = "") String operator){

        //Kiểm tra đầu vào
        if (firstNumberStr.isEmpty()) {
            return ResponseEntity.badRequest().body("First number cannot empty.");
        } else if (secondNumberStr.isEmpty()) {
            return ResponseEntity.badRequest().body("Second number cannot empty.");
        } else if (!isDouble(firstNumberStr)) {
            return ResponseEntity.badRequest().body("First number must be numeric.");
        } else if (!isDouble(secondNumberStr)) {
            return ResponseEntity.badRequest().body("Second number must be numeric.");
        }

        //Chuyển String thành double
        double firstNumber = Double.parseDouble(firstNumberStr);
        double secondNumber = Double.parseDouble(secondNumberStr);
        double result;

        //Thực hiện tính toán
        switch (operator) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> {
                if (secondNumber == 0) {
                    return ResponseEntity.badRequest().body("Division by zero is not allowed");
                }
                result = firstNumber / secondNumber;
            }
            default -> {
                return ResponseEntity.badRequest().body("Invalid operator");
            }
        }

        //Trả về kết quả
        return ResponseEntity.ok("Resutl: " + result);
    }

    //Kiểm tra số thực
    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
