package com.ssafy.fitcha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.fitcha.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(loginInterceptor) // 인터셉터 등록
				.addPathPatterns("/**") // 매핑할 url 지정
				.excludePathPatterns("/user/login","/main"); // 제외할 url 지정
	}
	

}
