package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.exception.DuplicateResourceException;
import com.cob.exception.UserNotFoundException;
import com.cob.mapper.UserMapper;
import com.cob.model.TokenDetails;
import com.cob.model.UserDto;
import com.cob.model.ValidationResult;
import com.cob.repository.UserRepository;
import com.cob.util.JwtUtil;
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
        return jwtUtil.validateToken(token);
    }
}
