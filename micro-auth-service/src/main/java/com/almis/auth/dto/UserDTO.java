package com.almis.auth.dto;

import com.almis.auth.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Integer id;
  private String username;
  private String password;
  private String email;
  private boolean enabled;
  private boolean accountNonExpired;
  private boolean credentialsNonExpired;
  private boolean accountNonLocked;
  private List<Role> roles;
}
