package com.greenguardpro.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import com.greenguardpro.mvc.model.User;
import com.greenguardpro.mvc.service.UserService;

@Controller
public class RegistrationController {
	
	@Autowired
	private UserService userservice;
	
	
	
	@GetMapping("/Register")
    public String showRegistrationForm() {
        return "Register";
    }

    @PostMapping("/Register")
    public String registerUser(@Valid @ModelAttribute User user ,Model model) {
    	
    	try {
			userservice.saveUser(user);
			return "redirect:/Login";
		} catch (Exception e) {
			model.addAttribute("errorMessage", "Username already exists or an error may have occured !");
			return "Register";
		}
    	
    	
    }
    	 
    		

}
