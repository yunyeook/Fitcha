package com.ssafy.fitcha.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.Weather;
import com.ssafy.fitcha.model.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

	// 생성자 의존성 주입
	private final WeatherService weatherService; 

	public WeatherController(WeatherService  weatherService) {
		this.weatherService = weatherService;
        System.out.println("WeatherService is injected: " + (weatherService != null)); // 주입 확인

	}
	
	@GetMapping
	// 프론트에서 유저의 현재 위도 경도 정보 받아오기
	public ResponseEntity<Weather> getWeather(@RequestParam double lat, @RequestParam double lon) {
	    System.out.println("WeatherController getWeather 호출됨! lat: " + lat + ", lon: " + lon);  // 로그 추가
		System.out.println(lat);
		 Weather currentWeather = weatherService.getCurrentWeather(lat, lon);
		 return ResponseEntity.ok(currentWeather);
	}
	
	

}
