package com.greenguardpro.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class StartController {
	
	
	public String index() {
		return "index";
	}
	
	@GetMapping("contact")
	public String contact() {
		return "contact";
	}
	
	@GetMapping("tips")
	public String tips() {
		return "tips";
	}
	
	@GetMapping("Plants-info")
	public String PlantsInfo() {
		return "Plants-info";
	}
	
	@GetMapping("heat-info")
	public String HeatInfo() {
		return "heat-info";
	}
	
	@GetMapping("humidity-info")
	public String HumidityInfo() {
		return "humidity-info";
	}
	
	@GetMapping("watering-info")
	public String WateringInfo() {
		return "watering-info";
	}

}
