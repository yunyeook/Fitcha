package com.ssafy.fitcha.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.fitcha.model.dao.UserDao;
import com.ssafy.fitcha.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	// 생성자 의존성 주입
	private final UserDao userDao;
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// 유저 회원가입
	@Override
	public boolean registUser(User user) {
		
		return 1 == userDao.insertUser(user);
	}
	
	// 유저 탈퇴
	@Override
	public boolean deleteUser(int userBoardId) {
		return 1 == userDao.deleteUser(userBoardId);
	}

}
