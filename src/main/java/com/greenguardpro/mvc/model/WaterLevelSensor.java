package com.greenguardpro.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class WaterLevelSensor {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private boolean waterState;
	
	private float waterLevel;
	
	private float waterMaxLevel;
	
	private float waterMinLevel;

	
	
	// Constructors
	public WaterLevelSensor() {
		
	}

	public WaterLevelSensor(User user, float waterLevel) {
		super();
		this.user = user;
		this.waterState = true;
		this.waterLevel = waterLevel;
		this.waterMaxLevel = 0;
		this.waterMinLevel = 0;
	}

	
	//Getters and Setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isWaterState() {
		return waterState;
	}

	public void setWaterState(boolean waterState) {
		this.waterState = waterState;
	}

	public float getWaterLevel() {
		return waterLevel;
	}

	public void setWaterLevel(float waterLevel) {
		this.waterLevel = waterLevel;
	}

	public float getWaterMaxLevel() {
		return waterMaxLevel;
	}

	public void setWaterMaxLevel(float waterMaxLevel) {
		this.waterMaxLevel = waterMaxLevel;
	}

	public float getWaterMinLevel() {
		return waterMinLevel;
	}

	public void setWaterMinLevel(float waterMinLevel) {
		this.waterMinLevel = waterMinLevel;
	}

	public Long getId() {
		return id;
	}

	
	// toString
	@Override
	public String toString() {
		return "WaterLevelSensor [id=" + id + ", user=" + user + ", waterState=" + waterState + ", waterLevel="
				+ waterLevel + ", waterMaxLevel=" + waterMaxLevel + ", waterMinLevel=" + waterMinLevel + "]";
	}
	
	
	
	
	
	
}
