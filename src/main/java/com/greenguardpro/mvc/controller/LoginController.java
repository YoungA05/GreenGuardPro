package com.greenguardpro.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	/*private final AuthenticationManager authenticationManager;

	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}*/

	@GetMapping("/login")
    public String showLoginForm() {
        return "Login";
    }
	
	/*@GetMapping("/logout")
	public String showLogoutForm() {
		return "redirect:/login?logout";
	}*/
	
	/*@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
		
		try {
		Authentication authenticationRequest =
			UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());
		Authentication authenticationResponse =
			this.authenticationManager.authenticate(authenticationRequest);
		// ...
		return ResponseEntity.ok().build();
		}catch(AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		
	}
	public record LoginRequest(String username, String password) {
	}*/
}
