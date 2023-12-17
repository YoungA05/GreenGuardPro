package com.greenguardpro.mvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.greenguardpro.mvc.model.HumiditySensor;
import com.greenguardpro.mvc.repository.HumiditySensorRepository;

@Service
public class HumiditySensorService {
	
	@Autowired
	private HumiditySensorRepository repository;
	
	
    public List<HumiditySensor> getAllHumiditySensors() {
		
		return repository.findAll();
		
	}
    
    public HumiditySensor getHumiditySensorById(Long id) {
		
		return repository.findById(id).orElse(null);
				
	}
    
    public HumiditySensor saveHumiditySensor(HumiditySensor sensor) {
		
		return this.repository.save(sensor);
		
	}
    
    public HumiditySensor updateHumiditySensor(Long id, HumiditySensor sensor) {
		
		HumiditySensor s = this.getHumiditySensorById(id);
		s = this.saveHumiditySensor(sensor);
		return s;
		
	}
    
    public void deleteHumiditySensor(Long id) {
		
		this.repository.deleteById(id);
	}



}
