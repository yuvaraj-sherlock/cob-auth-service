package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.exception.UserNotFoundException;
import com.cob.mapper.UserMapper;
import com.cob.model.TokenDetails;
import com.cob.model.UserDto;
import com.cob.repository.UserRepository;
import com.cob.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public AuthService(JwtUtil jwtUtil, UserRepository userRepository, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerUser(UserDto userDto) {
       UserEntity userEntity = userRepository.save(userMapper.toEntity(userDto));
       return userMapper.toDto(userEntity);
    }

    public TokenDetails generateTokenDetails(UserDto userDto) {
        String token = jwtUtil.generateToken(userDto.getUserName());
        return jwtUtil.getTokenDetails(token);
    }

    public boolean isValidUser(UserDto userDto) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findByUserName(userDto.getUserName());
        if (userEntity == null || userEntity.getPassword() == null) {
            throw new UserNotFoundException("User not found!");
        }
        return passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword());
    }

}
