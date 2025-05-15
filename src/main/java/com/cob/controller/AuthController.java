package com.cob.controller;

import com.cob.model.ErrorResponse;
import com.cob.model.UserDto;
import com.cob.model.TokenDetails;
import com.cob.service.AuthService;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody UserDto userDto) {
        TokenDetails tokenDetails = null;
        if (authService.isValidUser(userDto)) {
            tokenDetails = authService.generateTokenDetails(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenDetails);
        }
        return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),"The given credentials are not valid"));
    }
}
