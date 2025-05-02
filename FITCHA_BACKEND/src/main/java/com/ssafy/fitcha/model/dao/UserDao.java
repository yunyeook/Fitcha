package com.ssafy.fitcha.model.dao;

import com.ssafy.fitcha.model.dto.User;

public interface UserDao {
	
	// 유저 회원가입 (등록)
	int insertUser(User user);
	
	// 유저 탈퇴 (삭제)
	int deleteUser(int userBoardId);
	
	// 유저 로그인 
	User selectRegistedUser(User user);
	

}
