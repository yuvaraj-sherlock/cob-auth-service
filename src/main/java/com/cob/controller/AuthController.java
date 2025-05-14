package com.cob.controller;

import com.cob.model.UserDto;
import com.cob.model.TokenDetails;
import com.cob.service.AuthService;
import com.cob.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    public AuthController(JwtUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<String> registerUser(@RequestBody UserDto cobUser){
        String response = authService.registerUser(cobUser);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/generate")
    public ResponseEntity<TokenDetails> generateToken(){
        String token = jwtUtil.generateToken("Yuvaraj");
        Claims claims = jwtUtil.extractClaims(token);
        TokenDetails tokenDetails = new TokenDetails(token,claims.getExpiration(),claims.getIssuer());
        return ResponseEntity.ok(tokenDetails);
    }
}
