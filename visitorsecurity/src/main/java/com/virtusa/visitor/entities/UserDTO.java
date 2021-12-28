package com.virtusa.visitor.entities;

import java.util.Collection;

public class UserDTO {
	private String email;
	private String username;
	private String password;
	private String phno;
	private Collection<Role> roles;
	
	public UserDTO(String email, String username, String password, String phno, Collection<Role> roles) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.phno = phno;
		this.roles = roles;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhno() {
		return phno;
	}
	public void setPhno(String phno) {
		this.phno = phno;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public User convertToEntity(UserDTO userDTO) {
		return new User(userDTO.getEmail(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getPhno(), userDTO.getRoles());
	}
}
