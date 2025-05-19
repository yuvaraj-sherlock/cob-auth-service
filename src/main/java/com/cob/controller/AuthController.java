package com.cob.controller;

import com.cob.exception.UserNotFoundException;
import com.cob.model.ErrorResponse;
import com.cob.model.UserDto;
import com.cob.model.ValidationResult;
import com.cob.model.Views;
import com.cob.service.AuthService;
import com.fasterxml.jackson.annotation.JsonView;
import com.lib.token.cob.model.TokenDetails;
import jakarta.validation.Valid;
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
    @JsonView(Views.Response.class)
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid @JsonView(Views.Request.class) UserDto userDto) {
        UserDto response = authService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@Valid @RequestBody UserDto userDto) throws UserNotFoundException {
        TokenDetails tokenDetails = null;
        ValidationResult validUser = authService.isValidUser(userDto);
        if (validUser.valid()) {
            tokenDetails = authService.generateTokenDetails(validUser.userDto());
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenDetails);
        }
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),
                "The given credentials are not valid");
        return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<String> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Missing or invalid Authorization header");
        }
        String token = authHeader.substring(7).trim();// remove "Bearer " and trim spaces
        boolean isValid = authService.validateToken(token);
        if (isValid) {
            return ResponseEntity.ok("Token is Valid ");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid or expired");
        }
    }
}
