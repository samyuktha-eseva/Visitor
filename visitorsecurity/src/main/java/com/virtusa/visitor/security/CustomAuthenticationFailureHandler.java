package com.virtusa.visitor.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler
		implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
			request.getSession().setAttribute("loginerror", "Invalid Credentials");
			String targetUrl = "/login";
			redirectStrategy.sendRedirect(request, response, targetUrl);
	}

}
