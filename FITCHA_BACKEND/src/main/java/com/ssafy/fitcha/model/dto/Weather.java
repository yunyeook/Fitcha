package com.ssafy.fitcha.model.dto;

public class Weather {
	private String temperature; // 온도
	private String humidity; // 습도
	private String weather; // 날씨
	
	public Weather() {
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}
	
	
	
	
}
