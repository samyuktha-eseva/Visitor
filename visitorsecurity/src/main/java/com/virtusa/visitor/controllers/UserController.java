package com.virtusa.visitor.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.virtusa.visitor.entities.User;
import com.virtusa.visitor.entities.UserDTO;
import com.virtusa.visitor.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	private static final String LOGIN = "login";
	private static final String REGISTRATION = "registration";
	private static final String USER_HOME = "redirect:/user/home";
	private static final String ADMIN_HOME = "redirect:/admin/home";
	
	@GetMapping("/")
	public String homePage(@ModelAttribute("sessionout") String error, HttpServletRequest request) {
		request.getSession().removeAttribute("loginerror");
		return "index";
	}
	
	@GetMapping("/register")
	public String showRegistrationForm(Model model, HttpServletRequest request) {
		request.getSession().removeAttribute("loginerror");
		model.addAttribute("user", new User());
		return REGISTRATION;
	}
	
	@PostMapping("/register")
	public String doRegistration(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return REGISTRATION;
		}
		User existing = service.getUserByName(user.getUsername());
		if(existing!=null) {
			model.addAttribute("error", "Account already exists");
			return REGISTRATION;
		}
		service.saveUser(user);
		model.addAttribute("success", "You have Sucessfully Registered");
		return LOGIN;
	}
	
	@GetMapping("/login")
	public String showLoginForm(Model model, HttpServletRequest request) {
		
		User user = (User) request.getSession().getAttribute("loggedUser");
		if(user!=null) {
			if(request.isUserInRole("ROLE_ADMIN"))
				return ADMIN_HOME;
			else
				return USER_HOME;
		}
		
		model.addAttribute("user", new User());
		
		return LOGIN;
	}
	
	@GetMapping("/adminregister")
	public String showAdminRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "adminreg";
	}
	
	@PostMapping("/adminregister")
	public String doAdminRegistration(Model model, @Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return REGISTRATION;
		}
		User existing = service.getUserByName(user.getUsername());
		if(existing!=null) {
			model.addAttribute("error", "Account already exists");
			return REGISTRATION;
		}
		service.saveAdminUser(user);
		model.addAttribute("success", "You have Sucessfully Registered");
		return LOGIN;
	}
}