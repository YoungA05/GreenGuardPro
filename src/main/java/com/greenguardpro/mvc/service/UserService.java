package com.greenguardpro.mvc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.greenguardpro.mvc.model.User;
import com.greenguardpro.mvc.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
    public List<User> getAllUsers(){
		
		return this.repository.findAll();
	}
    
    public User getUserById(Long id) {
    	return this.repository.findById(id).orElse(null);
    }
    
    /*public User getUserByUsername( String username) {
    	return this.repository.findByUsername(username);
    }*/

    public User saveUser(User user) throws Exception {
    	
    	if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new Exception("Username already exists");
        }
    	
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	user.setRoles("ROLE_USER");
    	return this.repository.save(user);
    }
    
    public User updateUser(Long id, User user) throws Exception {
    	User u= this.getUserById(id);
    	u= this.saveUser(user);
    	return u;
    }
    
    public void deleteUser(Long id) {
    	this.repository.deleteById(id);
    }

	@Override
	public UserDetails loadUserByUsername(String UserName) throws UsernameNotFoundException {
		
		Optional<User> user = repository.findByUsername(UserName);
		return user.orElseThrow(()-> new UsernameNotFoundException("User not found with username" + UserName));
	}
}
