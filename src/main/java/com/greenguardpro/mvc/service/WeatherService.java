package com.greenguardpro.mvc.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.greenguardpro.mvc.model.WeatherResponse;

@Service
public class WeatherService {

	@Value("${openweathermap.api-key}")
    private String apiKey;
	
	@Value("${openweathermap.units}")
    private String units; //  We set this to "metric" for Celsius

    private final WebClient webClient;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5/weather").build();
    }

    public Double getCurrentTemperature(String city) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", city)
                        .queryParam("appid", apiKey)
                        .queryParam("units", units)
                        .build())
                .retrieve()
                .bodyToMono(WeatherResponse.class)
                .block()
                .getMain().getTemp();
    }
}
