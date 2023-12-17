package com.greenguardpro.mvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.greenguardpro.mvc.model.User;


@Controller
//@RequestMapping("/")
public class StartController {
	
	@GetMapping("/index")
	public String index(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		float temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
	
		return "index";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		float temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		
		return "contact";
	}
	
	@GetMapping("/tips")
	public String tips(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		float temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		
		return "tips";
	}
	
	@GetMapping("/Plants-info")
	public String PlantsInfo(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		float temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		
		return "Plants-info";
	}
	
	@GetMapping("/heat-info")
	public String HeatInfo() {
		return "/heat-info";
	}
	
	@GetMapping("/humidity-info")
	public String HumidityInfo() {
		return "humidity-info";
	}
	
	@GetMapping("/watering-info")
	public String WateringInfo() {
		return "watering-info";
	}

}
