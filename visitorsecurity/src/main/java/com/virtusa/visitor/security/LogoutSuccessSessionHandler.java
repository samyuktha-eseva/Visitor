package com.virtusa.visitor.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LogoutSuccessSessionHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	request.getSession().removeAttribute("uid");
    	request.getSession().removeAttribute("username");
    	request.getSession().removeAttribute("tempusername");
    	request.getSession().removeAttribute("loggedUser");
    	super.onLogoutSuccess(request,response,authentication);
    }
}
