package com.almis.fmb.auth.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "enabled")
	private boolean enabled;
	@Column(name = "accountNonExpired")
	private boolean accountNonExpired;
	@Column(name = "credentialsNonExpired")
	private boolean credentialsNonExpired;
	@Column(name = "accountNonLocked")
	private boolean accountNonLocked;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	public UserEntity(UserEntity userEntity) {
		this.username = userEntity.getUsername();
		this.password = userEntity.getPassword();
		this.email = userEntity.getEmail();
		this.enabled = userEntity.isEnabled();
		this.accountNonExpired = userEntity.isAccountNonExpired();
		this.credentialsNonExpired = userEntity.isCredentialsNonExpired();
		this.accountNonLocked = userEntity.isAccountNonLocked();
		this.roles = userEntity.getRoles();
	}
}
