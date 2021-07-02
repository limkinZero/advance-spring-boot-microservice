package com.almis.fmb.auth.controller;

import com.almis.fmb.auth.model.UserEntity;
import com.almis.fmb.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping(value = "/find/by-name")
  public ResponseEntity<UserEntity> getUserByName(@RequestParam String userName) {
    UserEntity user = userService.findUserByName(userName);
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

}
