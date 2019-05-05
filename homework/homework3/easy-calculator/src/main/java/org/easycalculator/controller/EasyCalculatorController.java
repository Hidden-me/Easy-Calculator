package org.easycalculator.controller;

import org.easycalculator.entry.Calculator;
import org.easycalculator.lib.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class EasyCalculatorController {
    @Autowired
    private LoginService login;
    @GetMapping
    public ModelAndView getCalculatorView(){
        Map<String, Object> param = new HashMap<>();
        param.put("username", "test");
        Map<String, Object> res = login.doLogin(param);
        boolean succ = (Boolean) res.get("success");
        if(!succ){
            return null;
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    @PostMapping("get_answer")
    public Map<String, Object> getResult(@RequestBody Map<String,Object> req){
        String expression = (String) req.get("expression");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> param = new HashMap<>();
        param.put("username", "test");
        Map<String, Object> res = login.doLogin(param);
        boolean succ = (Boolean) res.get("success");
        if(!succ){
            result.put("answer", "login failed");
            return result;
        }
        HttpSession ss = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        Calculator cal = (Calculator) ss.getAttribute("calculator");
        if(cal == null){
            cal = new Calculator();
            ss.setAttribute("calculator", cal);
        }
        String answer = cal.evaluate(expression);
        result.put("answer", answer);
        return result;
    }
}
