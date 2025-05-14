package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.mapper.UserMapper;
import com.cob.model.TokenDetails;
import com.cob.model.UserDto;
import com.cob.repository.UserRepository;
import com.cob.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(JwtUtil jwtUtil, UserRepository userRepository, UserMapper userMapper) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto registerUser(UserDto userDto) {
       UserEntity userEntity = userRepository.save(userMapper.toEntity(userDto));
       return userMapper.toDto(userEntity);
    }

    public TokenDetails generateTokenDetails() {
        String token = jwtUtil.generateToken("Yuvaraj");
        Claims claims = jwtUtil.extractClaims(token);
        TokenDetails tokenDetails = new TokenDetails();
        tokenDetails.setToken(token);
        tokenDetails.setIssuer(claims.getIssuer());
        tokenDetails.setExpireAt(claims.getExpiration());
        return tokenDetails;
    }
}
