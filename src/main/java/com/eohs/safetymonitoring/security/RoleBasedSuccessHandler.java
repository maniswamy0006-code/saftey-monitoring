package com.eohs.safetymonitoring.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class RoleBasedSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains("ROLE_WORKER")) {
            response.sendRedirect("/worker/dashboard");
        } else if (roles.contains("ROLE_ENGINEER")) {
            response.sendRedirect("/engineer/dashboard");
        } else if (roles.contains("ROLE_MANAGEMENT")) {
            response.sendRedirect("/management/dashboard");
        } else {
            response.sendRedirect("/");
        }
    }
}
