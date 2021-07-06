package com.almis.auth.repository;

import com.almis.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Integer> {

  Optional<UserEntity> findByUsername(String userName);
}
