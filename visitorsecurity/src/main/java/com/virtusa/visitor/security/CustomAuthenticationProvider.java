package com.virtusa.visitor.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.virtusa.visitor.services.UserService;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	UserService service;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
    	String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        UserDetails user = null;
        try {
        	user = userDetailsService.loadUserByUsername(username);	
        } catch(UsernameNotFoundException exception) {
        	throw new BadCredentialsException("Invalid Credentials");
        }
        
        if(!service.match(password, user.getPassword())) {
        	throw new BadCredentialsException("Invalid Credentials");
        }
      
        return createSuccessfulAuthentication(authentication, user);
        
	}
	
	private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserDetails user) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
	}
	 
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
