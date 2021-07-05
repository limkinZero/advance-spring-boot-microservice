package com.almis.fmb.auth.controller;

import com.almis.fmb.auth.common.message.BaseResponse;
import com.almis.fmb.auth.dto.UserDTO;
import com.almis.fmb.auth.entity.UserEntity;
import com.almis.fmb.auth.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_admin')")
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

  @GetMapping(value = "/find/all")
  public ResponseEntity<List<UserEntity>> getUsers() {
    List<UserEntity> userList = userService.findAll();
    return new ResponseEntity<>(userList, HttpStatus.OK);
  }

  @PostMapping(value = { "/add" })
  public ResponseEntity<BaseResponse> addUser(@Valid @RequestBody UserDTO userDTO) {
    BaseResponse response = userService.addUser(userDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(value = { "/update" })
  public ResponseEntity<BaseResponse> updateUser(@Valid @RequestBody UserDTO userDTO) {
    BaseResponse response = userService.updateUser(userDTO);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping(value = "/delete/{id}")
  public ResponseEntity<BaseResponse> deleteUserById(@PathVariable("id") Integer id) {
    BaseResponse response = userService.deleteUser(id);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
