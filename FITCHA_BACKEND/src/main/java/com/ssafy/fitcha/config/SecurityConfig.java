package com.ssafy.fitcha.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ssafy.fitcha.security.oauth.CustomOAuth2UserService;
import com.ssafy.fitcha.security.oauth.CustomOidcUserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	@Autowired 
	private CustomOidcUserService   customOidcUserService;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and() // CORS 설정 활성화
				.csrf().disable() // CSRF 보호 비활성화
				.authorizeHttpRequests(authorize -> authorize // 요청 권한 설정 시작
//						.requestMatchers("/**").permitAll() // 경로 모두 허용 : 

						// **이어도 .html같은 건 인증안된다고 생각해 아래에 설정한 login.html로 이동시켜버려서 백엔드 개발동안만 사용..
						.requestMatchers("/login.html", "/main.html", "/signup.html", "/oauth2/**"  ).permitAll()

						.anyRequest().authenticated() // 그 외 모든 요청 인증 필요
				).oauth2Login(oauth2 -> oauth2 // OAuth2 로그인 설정 시작
						// @Controller 나 @GetMapping("/login") 을 만들어서,
						// 자체 HTML·템플릿 뷰(예: 로그인 버튼, OAuth2 링크 등이 포함된 페이지)를 반환
						// 커스텀 로그인 페이지 지정(authorize.requestMatcher에 추가한 경로)
						.loginPage("/login.html")
						.userInfoEndpoint(userInfo -> userInfo
								.oidcUserService(customOidcUserService)     // ← Google(OpenID) 전용
								.userService(customOAuth2UserService)) //← OAuth2 전용 (카카오·네이버)  로그인 정보를 담음.
						.successHandler(new AuthenticationSuccessHandler() { // 👈 로그인 성공 후 리다이렉트 처리
							@Override
							public void onAuthenticationSuccess(HttpServletRequest request,
									HttpServletResponse response, Authentication authentication)
									throws IOException, ServletException {
								String status = (String) request.getSession().getAttribute("signupStatus");
								if ("true".equals(status)) {
									System.out.println("회원가입");
									response.sendRedirect("/signup.html"); // Vue 경로로 나중에 바꾸기
								} else {
									System.out.println("메인화면");
									response.sendRedirect("/main.html"); // Vue 경로로 나중에 바꾸기
								}
							}
						}));

		return http.build(); // SecurityFilterChain 반환

	}
}
