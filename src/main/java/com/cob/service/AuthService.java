package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.mapper.UserMapper;
import com.cob.model.UserDto;
import com.cob.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public String registerUser(UserDto userDto) {
        userRepository.save(userMapper.toEntity(userDto));
        return "Saved Successfully";
    }
}
