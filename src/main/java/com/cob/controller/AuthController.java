package com.cob.controller;

import com.cob.model.UserDto;
import com.cob.model.TokenDetails;
import com.cob.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register-user")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
        UserDto response = authService.registerUser(userDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/generate")
    public ResponseEntity<TokenDetails> generateToken(){
        TokenDetails tokenDetails = authService.generateTokenDetails();
        return ResponseEntity.ok(tokenDetails);
    }
}
