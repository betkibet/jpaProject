package com.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.models.User;
import com.services.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping({"/","/login"})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@GetMapping({"/registration"})
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	@PostMapping("/registration")
	public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView  = new ModelAndView();
		User userExists = userService.findUserByEmail(user.getEmail());
		if(userExists !=null) {
			bindingResult
				.rejectValue("email", "error.user", "User already exists");
		} if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage","User has been registered Successfully");
			modelAndView.addObject("user", user);
			modelAndView.setViewName("registration");
		}
		return modelAndView;
	}
	
	@GetMapping("/admin/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("adminMessage", "You are limited!!!");
		modelAndView.setViewName("admin/home");
		return modelAndView;
	}

}
