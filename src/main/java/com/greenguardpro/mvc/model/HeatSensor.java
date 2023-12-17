package com.greenguardpro.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class HeatSensor {
    
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 private float temperatureValue;

	 @OneToOne
	 @JoinColumn(name = "user_id")
	 private User user;
	 
	

	// Constructors 
	public HeatSensor() {
		
	}

	public HeatSensor(float temperatureValue, User user) {
		super();
		this.temperatureValue = temperatureValue;
		this.user = user;
	}


	//Getters and Setters
	public float getTemperatureValue() {
		return temperatureValue;
	}

	public void setTemperatureValue(float temperatureValue) {
		this.temperatureValue = temperatureValue;
	}

	public Long getId() {
		return id;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	// toString
	@Override
	public String toString() {
		return "HeatSensor [id=" + id + ", temperatureValue=" + temperatureValue + ", user=" + user + "]";
	}

	 
	
}
