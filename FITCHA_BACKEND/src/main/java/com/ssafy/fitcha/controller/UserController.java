package com.ssafy.fitcha.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.fitcha.model.dto.User;
import com.ssafy.fitcha.model.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	// 생성자 의존성 주입
	private final UserService userService;
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	// 유저 회원가입 (등록)
	@PostMapping
	public ResponseEntity<Void> registUser(@RequestBody User user){
		
		if(userService.registUser(user)) {
			
			return ResponseEntity.ok().build(); 
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	// 유저 탈퇴 (삭제)
	@DeleteMapping("/{userBoardId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userBoardId") int userBoardId){
		
		if(userService.deleteUser(userBoardId)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
	
}
