package com.virtusa.visitor.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.virtusa.visitor.entities.Role;
import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.entities.UserDTO;
import com.virtusa.visitor.repositories.UserRepository;

@Service
public class UserService implements UserServiceInterface {
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User getUserByName(String username) {
		return repo.findByUsername(username);
	}
	
	@Override
	public User saveUser(UserDTO user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return repo.save(user.convertToEntity(user));
	}
	
	@Override
	public User saveAdminUser(UserDTO user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
		return repo.save(user.convertToEntity(user));
	}
	
	@Override
	public String encode(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean match(String password, String hashedPassword) {
		return passwordEncoder.matches(password, hashedPassword);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), 
				user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
}
