package com.almis.fmb.auth.service;

import com.almis.fmb.auth.common.exception.ElementNotFoundException;
import com.almis.fmb.auth.model.UserEntity;
import com.almis.fmb.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity findUserByName(String userName) {
    return userRepository.findByUsername(userName)
            .orElseThrow(() -> new ElementNotFoundException("User not found"));
  }
}
