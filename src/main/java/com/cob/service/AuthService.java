package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.exception.DuplicateResourceException;
import com.cob.exception.UserNotFoundException;
import com.cob.mapper.UserMapper;
import com.cob.model.UserDto;
import com.cob.model.ValidationResult;
import com.cob.repository.UserRepository;
import com.cob.util.TokenBlacklist;
import com.lib.token.cob.model.TokenDetails;
import com.lib.token.cob.util.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(JwtUtil jwtUtil, UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto registerUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserName(userDto.getUserName());
        if (userEntity == null) {
            userDto = assignUserRole(userDto);
            userEntity = userRepository.save(userMapper.toEntity(userDto));
        }else{
            throw new DuplicateResourceException("UserName '" + userDto.getUserName() + "' already exists");
        }
       return userMapper.toDto(userEntity);
    }

    public TokenDetails generateTokenDetails(UserDto userDto) {
        String token = jwtUtil.generateToken(userDto);
        return jwtUtil.getTokenDetails(token);
    }

    public ValidationResult isValidUser(UserDto userDto) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(userDto.getUserName());
        if (userEntity == null || userEntity.getPassword() == null) {
            throw new UserNotFoundException("User not found!");
        }
        boolean isValid = passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword());
        return new ValidationResult(isValid,userMapper.toDto(userEntity));
    }

    public boolean validateToken(String token) {
        return !TokenBlacklist.isBlacklisted(token) && jwtUtil.validateToken(token);
    }

    private UserDto assignUserRole(UserDto userDto){
        String role = "ROLE_USER"; // default
        if ("ADMIN-2024-SECRET".equals(userDto.getAdminSecretCode())) {
            role = "ROLE_ADMIN";
        }
        userDto.setRole(role);
        return userDto;
    }

    public void blacklistToken(String token) {
        TokenBlacklist.blacklistToken(token);
    }
}
