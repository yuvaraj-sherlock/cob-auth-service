package com.cob.mapper;

import com.cob.entity.UserEntity;
import com.cob.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .userName(userEntity.getUserName())
                .password(userEntity.getPassword())
                .build();
    }
    public UserEntity toEntity(UserDto userDto){
        return UserEntity.builder()
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .build();
    }
}
