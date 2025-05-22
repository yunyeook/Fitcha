package com.ssafy.fitcha.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
	            .requestMatchers("/user/login", "/user/signup", "/oauth2/**","/youtube/**").permitAll()
	            .anyRequest().authenticated()
	        )
	        // 로그인 페이지 경로는 서버 내부 경로로 지정하거나 생략
	        //.loginPage("/login") // 만약 백엔드에서 로그인 페이지 제공 시
	        .oauth2Login(oauth2 -> oauth2
	            .userInfoEndpoint(userInfo -> userInfo
	                .oidcUserService(customOidcUserService)
	                .userService(customOAuth2UserService)
	            )
	            .successHandler((request, response, authentication) -> {
	                String token = jwtUtil.createToken(authentication.getName());
	                response.sendRedirect("http://localhost:5173/oauth-success?token=" + token);
	            })
	        )
	        .exceptionHandling()
	            .authenticationEntryPoint((request, response, authException) -> {
	                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	            });

	    http.addFilterBefore(new JwtAuthenticationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

	    return http.build();

		
		
		// 이전 코드 
//		http.cors().and() // CORS 설정 활성화
//				.csrf().disable() // CSRF 보호 비활성화
//				.authorizeHttpRequests(authorize -> authorize // 요청 권한 설정 시작
//						.requestMatchers("/**").permitAll() // 경로 모두 허용 :
//
//						// **이어도 .html같은 건 인증안된다고 생각해 아래에 설정한 login.html로 이동시켜버려서 백엔드 개발동안만 사용..
//						.requestMatchers("/login.html", "/main.html", "/signup.html").permitAll().anyRequest()
//						.authenticated() // 그 외 모든 요청 인증 필요
//				).oauth2Login(oauth2 -> oauth2 // OAuth2 로그인 설정 시작
//						// @Controller 나 @GetMapping("/login") 을 만들어서,
//						// 자체 HTML·템플릿 뷰(예: 로그인 버튼, OAuth2 링크 등이 포함된 페이지)를 반환
//						// 커스텀 로그인 페이지 지정(authorize.requestMatcher에 추가한 경로)
//						.loginPage("/login.html")
//						.userInfoEndpoint(userInfo -> userInfo.oidcUserService(customOidcUserService) // ←
//																										// Google(OpenID)
//																										// 전용
//								.userService(customOAuth2UserService)) // ← OAuth2 전용 (카카오·네이버) 로그인 정보를 담음.
//						.successHandler(new AuthenticationSuccessHandler() { // 👈 로그인 성공 후 리다이렉트 처리
//							@Override
//							public void onAuthenticationSuccess(HttpServletRequest request,
//									HttpServletResponse response, Authentication authentication)
//									throws IOException, ServletException {
//								String status = (String) request.getSession().getAttribute("signupStatus");
//								if ("true".equals(status)) {
//									System.out.println("회원가입");
//									response.sendRedirect("/signup.html"); // Vue 경로로 나중에 바꾸기
//								} else {
//									System.out.println("메인화면");
//									response.sendRedirect("/main.html"); // Vue 경로로 나중에 바꾸기
//								}
//							}
//						}));
//
//		return http.build(); // SecurityFilterChain 반환

	}
}
