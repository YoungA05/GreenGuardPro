package com.greenguardpro.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.greenguardpro.mvc.model.User;
import com.greenguardpro.mvc.service.ESP32CommunicationService;
import com.greenguardpro.mvc.service.UserService;
import com.greenguardpro.mvc.service.WeatherService;



@Controller
//@RequestMapping("/")
@SessionAttributes("userFormSubmitted")
public class StartController {
	
	@Autowired
	private UserService userservice;
	@Autowired
	private WeatherService weatherservice;
	@Autowired
	private ESP32CommunicationService uCcomms;
	
	 @ModelAttribute("userFormSubmitted")
	    public Boolean userFormSubmitted() {
	        return false;
	    }
	 @ModelAttribute("editMode")
	    public Boolean editMode() {
	        return false;
	    }
	 
	 
	
	@GetMapping("/index")
	public String index(Model model) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		user.getHeatSensor().setTemperatureValue(weatherservice.getCurrentTemperature("Hamburg"));
		userservice.updateUser(user.getIdUser(), user);
		Double temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
        model.addAttribute("User", user);
        
        uCcomms.receiveFromESP32(user);
        
        if (user.getHumiditySensors().get(0).getHumidityValue() != 0 && user.getHumiditySensors().get(0).getHumidityMinValue() != 0 && user.getHumiditySensors().get(0).getHumidityMaxValue() != 0) {
            if (user.getHumiditySensors().get(0).getHumidityValue() < user.getHumiditySensors().get(0).getHumidityMinValue() || user.getHumiditySensors().get(0).getHumidityValue() > user.getHumiditySensors().get(0).getHumidityMaxValue()) {
                model.addAttribute("humidityOutOfRangeSensor1", true);
            }
        }
        if (user.getHumiditySensors().get(1).getHumidityValue() != 0 && user.getHumiditySensors().get(1).getHumidityMinValue() != 0 && user.getHumiditySensors().get(1).getHumidityMaxValue() != 0) {
            if (user.getHumiditySensors().get(1).getHumidityValue() < user.getHumiditySensors().get(1).getHumidityMinValue() || user.getHumiditySensors().get(1).getHumidityValue() > user.getHumiditySensors().get(1).getHumidityMaxValue()) {
                model.addAttribute("humidityOutOfRangeSensor2", true);
            }
        }
        if (user.getHumiditySensors().get(2).getHumidityValue() != 0 && user.getHumiditySensors().get(2).getHumidityMinValue() != 0 && user.getHumiditySensors().get(2).getHumidityMaxValue() != 0) {
            if (user.getHumiditySensors().get(2).getHumidityValue() < user.getHumiditySensors().get(2).getHumidityMinValue() || user.getHumiditySensors().get(2).getHumidityValue() > user.getHumiditySensors().get(2).getHumidityMaxValue()) {
                model.addAttribute("humidityOutOfRangeSensor3", true);
            }
        }
        if (user.getHumiditySensors().get(3).getHumidityValue() != 0 && user.getHumiditySensors().get(3).getHumidityMinValue() != 0 && user.getHumiditySensors().get(3).getHumidityMaxValue() != 0) {
            if (user.getHumiditySensors().get(3).getHumidityValue() < user.getHumiditySensors().get(3).getHumidityMinValue() || user.getHumiditySensors().get(3).getHumidityValue() > user.getHumiditySensors().get(3).getHumidityMaxValue()) {
                model.addAttribute("humidityOutOfRangeSensor4", true);
            }
        }
        
		return "index";
	}
	
	@PostMapping("/submitForm")
	public String submitForm(@RequestParam( name = "name1", required = false, defaultValue = "")String name1,@RequestParam( name = "name2", required = false, defaultValue = "") String name2,
			                 @RequestParam( name = "name3", required = false, defaultValue = "") String name3, @RequestParam( name = "name4", required = false, defaultValue = "") String name4,
			                 @RequestParam int interv,@RequestParam( name = "Max1", required = false, defaultValue = "0") short Max1, @RequestParam( name = "Max2", required = false, defaultValue = "0")short Max2,
			                 @RequestParam( name = "Max3", required = false, defaultValue = "0") short Max3,@RequestParam( name = "Max4", required = false, defaultValue = "0") short Max4,
			                 @RequestParam( name = "Min1", required = false, defaultValue = "0") short Min1,@RequestParam( name = "Min2", required = false, defaultValue = "0") short Min2,
			                 @RequestParam( name = "Min3", required = false, defaultValue = "0") short Min3,@RequestParam( name = "Min4", required = false, defaultValue = "0") short Min4,
			                 @RequestParam(required = false, defaultValue = "false") boolean enableSensor1,@RequestParam(required = false, defaultValue = "false") boolean enableSensor2,
			                 @RequestParam(required = false, defaultValue = "false") boolean enableSensor3,@RequestParam(required = false, defaultValue = "false") boolean enableSensor4, Model model) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		
		user.setUpdateInterval(interv);
		
		user.getHumiditySensors().get(0).setExisting(enableSensor1);
		user.getHumiditySensors().get(0).setPlantName(name1);
		user.getHumiditySensors().get(0).setHumidityMinValue(Min1);
		user.getHumiditySensors().get(0).setHumidityMaxValue(Max1);
		
		user.getHumiditySensors().get(1).setExisting(enableSensor2);
		user.getHumiditySensors().get(1).setPlantName(name2);
		user.getHumiditySensors().get(1).setHumidityMinValue(Min2);
		user.getHumiditySensors().get(1).setHumidityMaxValue(Max2);
		
		user.getHumiditySensors().get(2).setExisting(enableSensor3);
		user.getHumiditySensors().get(2).setPlantName(name3);
		user.getHumiditySensors().get(2).setHumidityMinValue(Min3);
		user.getHumiditySensors().get(2).setHumidityMaxValue(Max3);
		
		user.getHumiditySensors().get(3).setExisting(enableSensor4);
		user.getHumiditySensors().get(3).setPlantName(name4);
		user.getHumiditySensors().get(3).setHumidityMinValue(Min4);
		user.getHumiditySensors().get(3).setHumidityMaxValue(Max4);
		
		userservice.updateUser(user.getIdUser(), user);
		
		//model.addAttribute("submitted", true);
		model.addAttribute("userFormSubmitted", true);
		model.addAttribute("User", user);
		
		Double temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
        
        // Sending informations to the uController
        uCcomms.sendToESP32(user);
		
		return "index";
	}
	
	@GetMapping("/editForm")
	public String editForm(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		Double temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		model.addAttribute("userFormSubmitted", false);
		return "index";
	}
	
	
	
	@GetMapping("/contact")
	public String contact(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		Double temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		
		return "contact";
	}
	
	@GetMapping("/tips")
	public String tips(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		Double temperature = user.getHeatSensor().getTemperatureValue();
		boolean waterLevel = user.getWaterLevelSensor().isWaterState();
		
		model.addAttribute("temperature", temperature);
        model.addAttribute("waterLevel", waterLevel);
		
		return "tips";
	}
	
	@GetMapping("/Plants-info")
	public String PlantsInfo(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User)authentication.getPrincipal();
		
		Double temperature = user.getHeatSensor().getTemperatureValue();
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
