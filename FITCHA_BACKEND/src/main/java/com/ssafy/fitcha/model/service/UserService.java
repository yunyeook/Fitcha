package com.ssafy.fitcha.model.service;

import com.ssafy.fitcha.model.dto.User;

public interface UserService {

	// 유저 회원가입
	boolean registUser(User user);

	// 유저 탈퇴
	boolean deleteUser(int userBoardId);

	// 유저 로그인
	User login(User user);

}
