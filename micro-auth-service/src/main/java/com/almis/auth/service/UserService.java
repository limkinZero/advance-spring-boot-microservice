package com.almis.auth.service;

import com.almis.auth.common.exception.ElementNotFoundException;
import com.almis.auth.common.message.BaseResponse;
import com.almis.auth.common.util.BeanUtils;
import com.almis.auth.dto.UserDTO;
import com.almis.auth.entity.UserEntity;
import com.almis.auth.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ModelMapper modelMapper;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.modelMapper = modelMapper;
  }

  public UserEntity findUserByName(String userName) {
    return userRepository.findByUsername(userName)
            .orElseThrow(() -> new ElementNotFoundException("User not found"));
  }

  public List<UserEntity> findAll() {
    return userRepository.findAll();
  }

  public BaseResponse addUser(@Valid UserDTO userDTO) {
    UserEntity userEntity = dtoToEntity(userDTO);
    // Encode password
    if (userDTO.getPassword() != null) {
      userEntity.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    }
    userRepository.save(userEntity);
    return new BaseResponse("User has been saved successfully", HttpStatus.CREATED.value());
  }

  @Transactional
  public BaseResponse updateUser(@Valid UserDTO userDTO) throws ElementNotFoundException {

    Optional<UserEntity> userToUpdate = userRepository.findByUsername(userDTO.getUsername());

    if (userToUpdate.isPresent()) {
      // Update password
       if (userDTO.getPassword() != null) {
         userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
       }
       userRepository.save(mergeUserDtoToUserEntity(userToUpdate.get(), userDTO));
    } else throw new ElementNotFoundException("Update error. User not found");

    return new BaseResponse("User has been saved successfully", HttpStatus.CREATED.value());
  }

  @Transactional
  public BaseResponse deleteUser(Integer userId) {
    if (userRepository.findById(userId).isPresent()) {
      userRepository.deleteById(userId);
    } else throw new ElementNotFoundException("Delete error. User not found");
    return new BaseResponse("User has been deleted successfully", HttpStatus.OK.value());
  }

  private UserEntity dtoToEntity(UserDTO userDTO) {
    return modelMapper.map(userDTO, UserEntity.class);
  }

  private UserEntity mergeUserDtoToUserEntity(UserEntity userEntity, UserDTO userDTO) {
    BeanUtils.copyNonNullProperties(userDTO, userEntity);
    return userEntity;
  }
}
