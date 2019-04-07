package org.easycalculator.controller;

import org.easycalculator.entry.Calculator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@RequestMapping("/calculator")
public class EasyCalculatorController {
    private static Calculator cal = new Calculator();
    @GetMapping("/")
    public ModelAndView getCalculatorPage(){
        System.out.println("GET /calculator/");
        return new ModelAndView("index");
    }
    @PostMapping
    public String getResult(@RequestBody Map<String,Object> reqMap){
        System.out.println("POST /calculator/");
        String exp = (String) reqMap.get("expression");
        String result = cal.evaluate(exp);
        System.out.println(result);
        return result;
    }
}
