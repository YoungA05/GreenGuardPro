package com.greenguardpro.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greenguardpro.mvc.model.WaterLevelSensor;
import com.greenguardpro.mvc.repository.WaterLevelSensorRepository;

@Service
public class WaterLevelSensorService {
	
	@Autowired
	private WaterLevelSensorRepository repository;
	

   public List<WaterLevelSensor> getAllWaterLevelSensors() {
		
		return repository.findAll();
		
	}
   
   public WaterLevelSensor getWaterLevelSensorById(Long id) {
		
		return repository.findById(id).orElse(null);
				
	}
   
   public WaterLevelSensor saveWaterLevelSensor(WaterLevelSensor sensor) {
		
		return this.repository.save(sensor);
		
	}
   
   public WaterLevelSensor updateWaterLevelSensor(Long id, WaterLevelSensor sensor) {
		
		WaterLevelSensor s = this.getWaterLevelSensorById(id);
		s = this.saveWaterLevelSensor(sensor);
		return s;
		
	}
   
   public void deleteWaterLevelSensor(Long id) {
		
		this.repository.deleteById(id);
	}
}
