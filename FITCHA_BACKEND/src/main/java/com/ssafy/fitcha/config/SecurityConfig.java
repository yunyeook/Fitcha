package com.ssafy.fitcha.config;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.security.oauth.CustomOAuth2UserService;
import com.ssafy.fitcha.security.oauth.CustomOidcUserService;
import com.ssafy.fitcha.util.JwtUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	@Autowired
	private CustomOidcUserService customOidcUserService;
	@Autowired
	private JwtUtil jwtUtil;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http
	        .cors().and()
	        .csrf().disable()
	        .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .authorizeHttpRequests(auth -> auth
	        		//REST로그인, 회원가입,oauth2엔드포인트 허용
	            .requestMatchers("/user/login", "/user/signup", "/oauth2/**")
	            .permitAll()
	            .anyRequest()
	            .authenticated()
	        )
	        .oauth2Login(oauth2 -> oauth2
	        	 // 인가 요청 시작( ex. GET /oauth2/authorization/kakao )
                .authorizationEndpoint(endpoint -> 
                    endpoint.baseUri("/oauth2/authorization")
                )
                // 리다이렉션 URL 패턴 -> 설정을 해줘야 스프링 시큐리티가 가로채서 처리하니까 .
                .redirectionEndpoint(endpoints ->
                    endpoints.baseUri("/login/oauth2/code/*")
                )
	            .userInfoEndpoint(userInfo -> userInfo
	                .oidcUserService(customOidcUserService) // 구글 전용
	                .userService(customOAuth2UserService) // 카카오, 네이버 전용
	            )
	            //인증 성공 후 토큰 생성하여 Vue라우터에서 처리함.
	            .successHandler((request, response, authentication) -> {
	            	 String status = (String) request.getSession().getAttribute("signupStatus");
	            	 User user = (User) request.getSession().getAttribute("user");
	                String token = jwtUtil.createToken(authentication.getName());
	                String userId = user !=null? user.getUserId():"none";
	                String nickName=user!=null?user.getNickName():"none";

	                String redirectUrl = "http://localhost:5173/oauth-success" +
	                        "?token="    + URLEncoder.encode(token,    StandardCharsets.UTF_8) +
	                        "&userId="   + URLEncoder.encode(userId,   StandardCharsets.UTF_8) +
	                        "&nickName=" + URLEncoder.encode(nickName, StandardCharsets.UTF_8) +
	                        "&signup=" + URLEncoder.encode(status,   StandardCharsets.UTF_8);

	                    response.sendRedirect(redirectUrl);	            })
	        )
	        .exceptionHandling()
	            .authenticationEntryPoint((request, response, authException) -> {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	            });

	    http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

	    return http.build();


	}
}

