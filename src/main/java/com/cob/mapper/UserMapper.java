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

    public UserDto toDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setUserName(userEntity.getUserName());
        userDto.setPassword(userEntity.getPassword());
        return userDto;
    }
    public UserEntity toEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userEntity;
    }
}
