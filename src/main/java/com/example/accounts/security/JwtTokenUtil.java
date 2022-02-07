package com.example.accounts.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {
    public boolean validate(String token) {
        return false;
    }

    public String getUsername(String token) {
        return getUsername(token);
    }
}
