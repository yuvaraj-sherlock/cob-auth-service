package com.cob.controller;

import com.cob.model.TokenDetails;
import com.cob.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class LoginController {
    
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/generate")
    public ResponseEntity<TokenDetails> generateToken(){
        String token = jwtUtil.generateToken("Yuvaraj");
        Claims claims = jwtUtil.extractClaims(token);
        TokenDetails tokenDetails = new TokenDetails(token,claims.getExpiration(),claims.getIssuer());
        return ResponseEntity.ok(tokenDetails);
    }
}
