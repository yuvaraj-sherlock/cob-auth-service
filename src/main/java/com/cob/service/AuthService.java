package com.cob.service;

import com.cob.entity.CobUserEntity;
import com.cob.model.CobUser;
import com.cob.repository.CobUserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CobUserRepository cobUserRepository;

    public AuthService(CobUserRepository cobUserRepository) {
        this.cobUserRepository = cobUserRepository;
    }

    public String registerUser(CobUser cobUser){
        CobUserEntity cobUserEntity = new CobUserEntity(cobUser.getUserName(),cobUser.getPassword());
        cobUserRepository.save(cobUserEntity);
        return "Saved Successfully";
    }
}
