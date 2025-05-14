package com.cob.repository;

import com.cob.entity.CobUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CobUserRepository extends JpaRepository<CobUserEntity, Integer> {
}
