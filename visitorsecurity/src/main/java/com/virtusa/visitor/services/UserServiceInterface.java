package com.virtusa.visitor.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.entities.UserDTO;

public interface UserServiceInterface extends UserDetailsService {
	
	public User getUserByName(String username);
	public User saveUser(UserDTO user);
	public User saveAdminUser(UserDTO user);
	public String encode(String password);
	public boolean match(String password, String hashedPassword);
	
}
