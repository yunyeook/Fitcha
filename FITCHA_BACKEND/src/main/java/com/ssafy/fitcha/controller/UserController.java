package com.ssafy.fitcha.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

	// 생성자 의존성 주입
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// 로그인/로그아웃은 상태 변경 작업(서버의 세션 상태를 바꿈)
	// 이런 상태 변화가 있는 작업은 POST 또는 DELETE로 처리하는 것이 REST 관점
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session) {

		// 로그인 요청 받은 id와 DB에 등록된 동일한 id를 가진 유저를 반환
		User registedUser = userService.login(user);

		// null이라면 로그인 요청 받은 id와 동일한 user가 없다는 것 -> 아이디 존재 X
		if (registedUser == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디가 존재하지 않습니다.");
		}

		// 아이디는 동일하지만 패스워드가 일치하지 않는 경우
		if (!user.getPassword().equals(registedUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
		}

		// 아이디와 패스워드 일치 -> 로그인 성공
		session.setAttribute("loginUser", registedUser);
		return ResponseEntity.ok("로그인 성공");

	}

	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
		session.invalidate(); // 세션 무효화 -> 로그아웃
		return ResponseEntity.ok("로그아웃 되었습니다.");
	}

	// 유저 회원가입 (등록)
	@PostMapping("/signup")
	public ResponseEntity<Void> registUser(@RequestBody User user) {

		if (userService.registUser(user)) {

			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	// 유저 탈퇴 (삭제)
	@DeleteMapping("/{userBoardId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userBoardId") int userBoardId) {

		if (userService.deleteUser(userBoardId)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.badRequest().build();
	}

	// 카카오 로그인/회원가 버튼 누른경우.
	@Value("${kakao.client-id}")
	private String clientId;

	@Value("${kakao.redirect-uri}")
	private String redirectUri;

	@GetMapping("/login/kakao")
	public ResponseEntity<String> getKakaoLoginUrl() {
		/*
		// 카카오 로그인 URL 생성
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri="
				+ redirectUri + "&response_type=code" + "&scope=profile_nickname,account_email,gender"
				+ "&prompt=login"; // prompt=login : 자동로그인을 허용하고 싶으면 제거하기
		System.out.println("카카오 로그인 URL: " + kakaoUrl);
		System.out.println();
		 */
		// 카카오 로그인 URL 생성
		String kakaoUrl = "https://kauth.kakao.com/oauth/authorize" + "?client_id=" + clientId + "&redirect_uri="
				+ redirectUri + "&response_type=code" + "&scope=profile_nickname,account_email"
				+ "&prompt=login"; // prompt=login : 자동로그인을 허용하고 싶으면 제거하기
		System.out.println("카카오 로그인 URL: " + kakaoUrl);
		System.out.println();
		// 프론트에 리다이렉션용 URL만 전달
		return ResponseEntity.ok(kakaoUrl);
	}

}
