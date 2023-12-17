package com.greenguardpro.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.greenguardpro.mvc.model.HumiditySensor;
import com.greenguardpro.mvc.model.User;

@CrossOrigin
@Controller
public class MCController {

	@PostMapping("/api/data")
	public ResponseEntity<String> receiveData(@RequestParam short humidity, @RequestParam boolean waterLevel){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		user.getWaterLevelSensor().setWaterState(waterLevel);
		for (HumiditySensor h : user.getHumiditySensors()) {
			h.setHumidityValue(humidity);
		}
		
		return ResponseEntity.ok("Data received and will be stored");
			
	}
	
	@GetMapping("/api/get")
	public ResponseEntity<Map<String, Object>> sendData(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("UpdateInterval", user.getUpdateInterval());
		for (HumiditySensor h : user.getHumiditySensors()) {
			values.put("MaxHumidityValue", h.getHumidityMaxValue());
			values.put("MinHumidityValue", h.getHumidityMinValue());
		}
		
		
		return ResponseEntity.ok(values);
			
	}
}
