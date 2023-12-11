package com.greenguardpro.mvc.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;

import jakarta.persistence.Entity;

@Entity
public class User implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUser;
	
	private String FirstName;
	
	private String LastName;
	
	private int Age;
	
	@Column( unique= true, nullable = false)
	private String username;
	
	private String Password;
	
	private String roles;
	
	
	//Constructors
	
	public User() {
		
	}


	public User(String firstName, String lastName, int age, String userName, String password, String Roles) {
		super();
		FirstName = firstName;
		LastName = lastName;
		Age = age;
		username = userName;
		Password = password;
		roles = Roles;
	}

    
	// Getters and Setters 
	
	public String getFirstName() {
		return FirstName;
	}


	public void setFirstName(String firstName) {
		FirstName = firstName;
	}


	public String getLastName() {
		return LastName;
	}


	public void setLastName(String lastName) {
		LastName = lastName;
	}


	public int getAge() {
		return Age;
	}


	public void setAge(int age) {
		Age = age;
	}


	/*public String getUserName() {
		return username;
	}*/


	public void setUserName(String userName) {
		username = userName;
	}


	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}


	public Long getIdUser() {
		return idUser;
	}
	
	public String getRoles() {
		return roles;
	}


	public void setRoles(String roles) {
		this.roles = roles;
	} 
	
	
	// ToString
	
	@Override
	public String toString() {
		
		return super.toString();
	}

    // Overriding UserDetails Methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		List<GrantedAuthority> authorities = Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return authorities;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	
	
	
	

}
