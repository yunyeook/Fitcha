package com.ssafy.fitcha.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/oauth")
public class KakaoLoginController {
	@Autowired
	private UserService userService;

	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	// 카카오 로그인 완료 후 카카오에서 인가코드를 포함한 Get요청을 내 서버에 보냄.
	// http://localhost:8080/oauth/kakao/callback?code=인가코드
	// : 지금은 백엔드로 받지만 프론트 작성시 redirect를 프론트로 해서 백엔드 거친 후 프론트가 응답받도록 처리하기.

	@GetMapping("/kakao/callback")
	public ResponseEntity<?> kakaoCallback(@RequestParam String code, HttpSession session) {
		// code: 사용자가 로그인 후 카카오가 보낸 인가 코드 (authorization_code)
//		System.out.println(">> 인가 코드 수신: " + code);
//		System.out.println();

		// 1. 인가코드를 바탕으로 카카오 서버에 액세스 토큰 요청하기 (내 서버에서 카카오로 http요청 보내기)

		// 1-1. HTTP 요청 보내는 도구 생성.
		RestTemplate restTemplate = new RestTemplate();

		// 1-2. HTTP요청 헤더 생성 후 폼데이터 전송방식임을 명시
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// 1-3. 카카오 API 명세에서 요구하는 값들 저장.
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>(); // POST 파라미터 구성
		params.add("grant_type", "authorization_code"); // 고정값
		params.add("client_id", clientId); // 카카오 REST API 키
		params.add("redirect_uri", redirectUri); // 등록한 redirect URI
		params.add("code", code); // 프론트에서 전달된 인가 코드

		// 1-4. 요청 파라미터와 헤더를 담은 HTTP 요청 엔티티 생성.
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(params, headers);

		// 1-5. POST 방식으로 카카오에 토큰 요청보내서 받기
		ResponseEntity<Map> tokenResponse = restTemplate.postForEntity("https://kauth.kakao.com/oauth/token",
				tokenRequest, Map.class);

		// 2. 사용자 액세스 토큰으로 카카오 서버에 사용자 정보 요청

		// 2-1. 사용자 액세스 토큰 추출
		String accessToken = (String) tokenResponse.getBody().get("access_token");

		// 2-2.사용자 정보 요청용 헤더 생성 후 폼데이터 전송방식임을 명시
		HttpHeaders userInfoHeaders = new HttpHeaders(); // 사용자 정보 요청용 헤더
		userInfoHeaders.setBearerAuth(accessToken); // Authorization: Bearer {token}
		userInfoHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		// 2-3. 헤더만 포함된 HTTP 요청 엔티티 생성.
		HttpEntity<?> userInfoRequest = new HttpEntity<>(userInfoHeaders);

		// 2-4. 사용자 정보 요청
		ResponseEntity<Map> userInfoResponse = restTemplate.exchange("https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST, userInfoRequest, Map.class);
		Map userInfo = userInfoResponse.getBody();

		System.out.println(">> userInfo: " + userInfo);

		Long kakaoId = Long.valueOf(String.valueOf(userInfo.get("id")));
		Map kakaoAccount = (Map) userInfo.get("kakao_account");
		String email = (String) kakaoAccount.get("email");
		String nickname = (String) ((Map) kakaoAccount.get("profile")).get("nickname");

//		// 확인 로그
//		System.out.println(">> email: " + email);
//		System.out.println(">> gender: " + gender);
//		System.out.println(">> nickname: " + nickname);
//		System.out.println();

		User user = new User();
		user.setUserId(email);
		user = userService.login(user);

		if (user == null) {
			// 프론트에게 "signup 필요함을" 응답 -> 프론트에서 signup으로 이동하도록 함.
			Map<String, Object> response = new HashMap<>();
			response.put("status", "signup");

			// 카카오 로그인시 수집한 계정정보와 닉네임정보를 프론트에 전달해 회원가입시 자동입력하도록 설정하기.
			User tmpUser = new User();
			tmpUser.setUserId(email);
			tmpUser.setNickName(nickname);
			response.put("user", tmpUser);

			return ResponseEntity.ok(response);
		} else {
			session.setAttribute("loginUser", user);
			return ResponseEntity.ok(Map.of("status", "success", "user", user));
		}
	}
}
// 1. 프론트에서 리다이렉트 받는다 
// 2. 프론트에서 code가져와서 백엔드 API 호출한다 << 
// 3 백엔드에서 카카오 auth 서버 찔러서사용자 정보 가져온다음에 응답준다. 
