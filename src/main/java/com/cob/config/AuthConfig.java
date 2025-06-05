package com.cob.config;

import com.lib.token.cob.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthConfig {

    private final String SECRET_KEY = System.getenv("JWT_SECRET");

    @Bean
    public JwtUtil jwtUtil(){
        if (SECRET_KEY == null || SECRET_KEY.isEmpty()) {
            throw new RuntimeException("Missing required environment variable: JWT_SECRET");
        }
        return new JwtUtil(SECRET_KEY, 3600000L); // 1 hour expiry in milliseconds(SECRET_KEY);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
