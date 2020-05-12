package com.codeup.springblogapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MathController {

    @GetMapping("/add/{num1}/and/{num2}")
    @ResponseBody
    public long add(@PathVariable int num1, @PathVariable int num2){
        return num1 + num2;
    }

    @GetMapping("/subtract/{num1}/from/{num2}")
    @ResponseBody
    public long subtract(@PathVariable int num1, @PathVariable int num2){
        return num2 - num1;
    }

    @GetMapping("/multiply/{num1}/and/{num2}")
    @ResponseBody
    public double multiply(@PathVariable int num1, @PathVariable int num2){
        return num1 * num2;
    }

    @GetMapping("/divide/{num1}/and/{num2}")
    @ResponseBody
    public double divide(@PathVariable int num1, @PathVariable int num2){
        return num1 / num2;
    }

    @GetMapping("/square/{num1}")
    @ResponseBody
    public double square(@PathVariable int num1){
        return num1 * num1;
    }
}
