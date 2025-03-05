package com.tomzxy.webQuiz.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import com.tomzxy.webQuiz.service.AuthenticationService;
import com.tomzxy.webQuiz.service.impl.AuthenticationImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.text.ParseException;

public class SecurityUtils {
    public static Jwt getCurrentJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt) {
            return (Jwt) authentication.getPrincipal();
        }
        return null;
    }

    public static String getUserNameFromCurrentJwt() {
        Jwt jwt = getCurrentJwt();
        return jwt != null ? jwt.getClaimAsString("sub") : null;
    }
}
