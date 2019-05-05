package org.hiddenme.loginservice.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("")
public class LoginController {
    @PostMapping
    public Map<String, Object> doLogin(@RequestBody Map<String, Object> req){
        Map<String, Object> resp = new HashMap<>();
        boolean succ = false;
        String username = (String) req.get("username");
        if(username != null && username.equals("test")){
            succ = true;
        }
        resp.put("success", succ);
        return resp;
    }
}
