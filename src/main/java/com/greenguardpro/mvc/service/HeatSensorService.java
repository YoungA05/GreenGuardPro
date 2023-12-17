package com.greenguardpro.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenguardpro.mvc.model.HeatSensor;
import com.greenguardpro.mvc.repository.HeatSensorRepository;

@Service
public class HeatSensorService {
	
	@Autowired
	private HeatSensorRepository repository;
	
	
	
	public List<HeatSensor> getAllHeatSensors() {
		
		return repository.findAll();
		
	}
	
	public HeatSensor getHeatSensorById(Long id) {
		
		return repository.findById(id).orElse(null);
				
	}
	
	public HeatSensor saveHeatSensor(HeatSensor sensor) {
		
		return this.repository.save(sensor);
		
	}
	
	public HeatSensor updateHeatSensor(Long id, HeatSensor sensor) {
		
		HeatSensor s = this.getHeatSensorById(id);
		s = this.saveHeatSensor(sensor);
		return s;
		
	}

	public void deleteHeatSensor(Long id) {
		
		this.repository.deleteById(id);
	}
}
