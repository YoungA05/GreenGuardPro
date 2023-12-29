package com.greenguardpro.mvc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class HumiditySensor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	private short humidityValue;
	
	private short humidityMaxValue;
	
	private short humidityMinValue;
	
	private String plantName;
	
	private boolean isExisting;

	
	//Constructors
	public HumiditySensor() {
		
	}

	public HumiditySensor(User user, short humidityValue) {
		super();
		this.user = user;
		this.humidityValue = humidityValue;
		this.humidityMaxValue = 0;
		this.humidityMinValue = 0;
		this.plantName = null;
		this.isExisting = false;
	}



	//Getters and Setters
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public short getHumidityValue() {
		return humidityValue;
	}

	public void setHumidityValue(short humidityValue) {
		this.humidityValue = humidityValue;
	}

	public short getHumidityMaxValue() {
		return humidityMaxValue;
	}

	public void setHumidityMaxValue(short humidityMaxValue) {
		this.humidityMaxValue = humidityMaxValue;
	}

	public short getHumidityMinValue() {
		return humidityMinValue;
	}

	public void setHumidityMinValue(short humidityMinValue) {
		this.humidityMinValue = humidityMinValue;
	}

	public Long getId() {
		return id;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}
	
	public boolean isExisting() {
		return isExisting;
	}

	public void setExisting(boolean isExisting) {
		this.isExisting = isExisting;
	}


	
	//toString
	@Override
	public String toString() {
		return "HumiditySensor [id=" + id + ", user=" + user + ", humidityValue=" + humidityValue
				+ ", humidityMaxValue=" + humidityMaxValue + ", humidityMinValue=" + humidityMinValue + ", plantName="
				+ plantName + ", exists=" + isExisting + "]";
	}

	

	

}
