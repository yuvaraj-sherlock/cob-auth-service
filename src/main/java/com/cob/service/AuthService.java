package com.cob.service;

import com.cob.entity.UserEntity;
import com.cob.model.UserDto;
import com.cob.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository cobUserRepository;

    public AuthService(UserRepository cobUserRepository) {
        this.cobUserRepository = cobUserRepository;
    }

    public String registerUser(UserDto cobUser){
        UserEntity cobUserEntity = new UserEntity(cobUser.getUserName(),cobUser.getPassword());
        cobUserRepository.save(cobUserEntity);
        return "Saved Successfully";
    }
}
