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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "username", nullable = false)
	private String username;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "enabled", nullable = false, columnDefinition = "boolean default 1")
	private boolean enabled;
	@Column(name = "accountNonExpired", nullable = false, columnDefinition = "boolean default true")
	private boolean accountNonExpired;
	@Column(name = "credentialsNonExpired", nullable = false, columnDefinition = "boolean default true")
	private boolean credentialsNonExpired;
	@Column(name = "accountNonLocked", nullable = false, columnDefinition = "boolean default 1")
	private boolean accountNonLocked;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles;

	public UserEntity(UserEntity userEntity) {
		this.id = userEntity.getId();
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
