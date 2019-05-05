package org.easycalculator.lib;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient("loginservice")
public interface LoginService {
    @PostMapping("/login")
    Map<String, Object> doLogin(@RequestBody Map<String, Object> req);
}
