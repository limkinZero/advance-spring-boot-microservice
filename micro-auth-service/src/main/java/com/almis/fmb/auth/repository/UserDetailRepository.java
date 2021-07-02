package com.almis.fmb.auth.repository;

import com.almis.fmb.auth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailRepository extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByUsername(String name);

}
