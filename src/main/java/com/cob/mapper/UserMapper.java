package com.cob.mapper;

import com.cob.entity.UserEntity;
import com.cob.model.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
}
