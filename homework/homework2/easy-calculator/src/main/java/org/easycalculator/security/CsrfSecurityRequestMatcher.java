package org.easycalculator.security;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class CsrfSecurityRequestMatcher implements RequestMatcher {
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    @Override
    public boolean matches(HttpServletRequest request) {
        List<String> unExecludeUrls = new ArrayList<>();
        if (unExecludeUrls != null && unExecludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            request.getParameter("");
            for (String url : unExecludeUrls) {
                if (servletPath.contains(url)) {
                    return true;
                }
            }
        }
        return allowedMethods.matcher(request.getMethod()).matches();
    }
}
