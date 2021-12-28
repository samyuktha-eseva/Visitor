package com.virtusa.visitor.security;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.repositories.UserRepository;

@Component
public class CustomSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
		implements AuthenticationSuccessHandler {
		
		@Autowired
		private UserRepository repo;
	 	
		private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        User usernew = repo.findByUsername(authentication.getName());
	        request.getSession().removeAttribute("loginerror");
	        request.getSession().setAttribute("loggedUser", usernew);
	        request.getSession().removeAttribute("sessionout");
	        
	        handle(request,response,authentication);
	        super.onAuthenticationSuccess(request, response, authentication);
	    }
	    
	    @Override
	    protected void handle(HttpServletRequest request, 
	    		HttpServletResponse response, Authentication authentication)
	    		throws IOException{
	    	String targetUrl = determineTargetUrl(authentication);
	    	if (response.isCommitted()) {
	            return;
	        }

	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }
	    
	    protected String determineTargetUrl(Authentication authentication) {
	    	Map<String, String> map = new HashMap<>();
	    	map.put("ROLE_USER", "/user/home");
	    	map.put("ROLE_ADMIN", "/admin/home");
	    	Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	    	for(GrantedAuthority grantedAuthority: authorities) {
	    		String authorityName = grantedAuthority.getAuthority();
	    		if(map.containsKey(authorityName)) {
	    			return map.get(authorityName);
	    		}
	    	}
	    	
	    	throw new IllegalStateException();
	    }
	    
	    
}

